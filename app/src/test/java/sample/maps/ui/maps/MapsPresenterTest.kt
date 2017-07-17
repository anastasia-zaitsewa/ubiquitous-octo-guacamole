package sample.maps.ui.maps

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
import sample.maps.model.Location

@RunWith(MockitoJUnitRunner::class)
class MapsPresenterTest {

    private val dummyLatLang = 10.0

    @Mock
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
    @Mock
    lateinit var saveLocationToRepositoryUseCase: SaveLocationToRepositoryUseCase
    @Mock
    lateinit var view: MapsView

    lateinit var presenter: MapsPresenter


    @Before
    fun setUp() {
        presenter = MapsPresenter(
                getCurrentLocationUseCase,
                saveLocationToRepositoryUseCase,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )

        val location = Location(dummyLatLang, dummyLatLang)
        given(getCurrentLocationUseCase.get())
                .willReturn(just(location))
        given(saveLocationToRepositoryUseCase.save(location))
                .willReturn(Completable.complete())
    }

    @Test
    fun loadLocation() {
        // When
        presenter.resume(view)

        // Then
        verify(view).updateState(MapsView.State(
                Location(dummyLatLang, dummyLatLang)
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
        // Given
        val location = Location(dummyLatLang, dummyLatLang)

        // When
        with(presenter) {
            resume(view)
            addLocationClicked(location)
        }

        verify(saveLocationToRepositoryUseCase).save(location)
    }

    @Test
    fun setViewListener() {
        // When
        presenter.resume(view)

        // Then
        verify(view).setListener(presenter)
    }
}