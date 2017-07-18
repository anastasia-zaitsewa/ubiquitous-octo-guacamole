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
import sample.maps.interactor.GetAllLocationsFromRepositoryUseCase
import sample.maps.interactor.GetCurrentLocationUseCase
import sample.maps.interactor.SaveLocationToRepositoryUseCase
import sample.maps.model.Location

@RunWith(MockitoJUnitRunner::class)
class MapsPresenterTest {

    val dummyLatLang = 10.0
    val location = Location(dummyLatLang, dummyLatLang)


    @Mock
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase
    @Mock
    lateinit var saveLocationToRepositoryUseCase: SaveLocationToRepositoryUseCase
    @Mock
    lateinit var getAllLocationsUseCase: GetAllLocationsFromRepositoryUseCase
    @Mock
    lateinit var view: MapsView

    lateinit var presenter: MapsPresenter


    @Before
    fun setUp() {
        presenter = MapsPresenter(
                getCurrentLocationUseCase,
                getAllLocationsUseCase,
                saveLocationToRepositoryUseCase,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )

        given(getCurrentLocationUseCase.get())
                .willReturn(just(location))
        given(saveLocationToRepositoryUseCase.save(location))
                .willReturn(Completable.complete())
        given(getAllLocationsUseCase.get())
                .willReturn(just(listOf(location, location)))
    }

    @Test
    fun loadLocation() {
        // When
        presenter.resume(view)

        // Then
        verify(view).updateState(MapsView.State(
                listOf(location, location)
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
        with(presenter) {
            resume(view)
            addLocationClicked()
        }

        // Then
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