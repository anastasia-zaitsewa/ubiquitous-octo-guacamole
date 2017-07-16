package sample.maps.ui.maps

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import io.reactivex.Observable.just
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.interactor.GetCurrentLocationUseCase
import sample.maps.interactor.SaveLocationToRepositoryUseCase

@RunWith(MockitoJUnitRunner::class)
class MapsPresenterTest {

    private val dummyLatLang = 10.0

    @Mock
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
    @Mock
    lateinit var saveLocationToRepositoryUseCase: SaveLocationToRepositoryUseCase
    @Mock
    lateinit var view: MapsView
    @Mock
    lateinit var location: Location

    lateinit var presenter: MapsPresenter


    @Before
    fun setUp() {
        presenter = MapsPresenter(
                getCurrentLocationUseCase,
                saveLocationToRepositoryUseCase,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )

        given(location.longitude)
                .willReturn(dummyLatLang)
        given(location.latitude)
                .willReturn(dummyLatLang)
        given(getCurrentLocationUseCase.get())
                .willReturn(just(location))
        given(saveLocationToRepositoryUseCase.save(LatLng(dummyLatLang, dummyLatLang)))
                .willReturn(Completable.complete())
    }

    @Test
    fun loadLocation() {
        // When
        presenter.resume(view)

        // Then
        verify(view).updateState(MapsView.State(
                LatLng(dummyLatLang, dummyLatLang)
        ))
    }

    @Test
    fun locationListClicked() {
        // When
        with(presenter) {
            resume(view)
            locationListClicked()
        }
        // Then
        verify(view).navigateLocationList()
    }

    @Test
    fun addLocationClicked() {
        // When
        val latLong = LatLng(dummyLatLang, dummyLatLang)
        with(presenter) {
            resume(view)
            addLocationClicked(latLong)
        }

        verify(saveLocationToRepositoryUseCase).save(latLong)
    }

    @Test
    fun setViewListener() {
        // When
        presenter.resume(view)

        // Then
        verify(view).setListener(presenter)
    }
}