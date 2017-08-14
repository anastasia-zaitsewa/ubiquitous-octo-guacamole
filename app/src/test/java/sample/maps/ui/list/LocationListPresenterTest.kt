package sample.maps.ui.list

import android.location.Location
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.interactor.GetAllLocationsFromRepositoryUseCase

@RunWith(MockitoJUnitRunner::class)
class LocationListPresenterTest {

    @Mock
    lateinit var location: Location

    @Mock
    lateinit  var getAllLocationsUseCase: GetAllLocationsFromRepositoryUseCase
    @Mock
    lateinit var view: LocationListView

    lateinit var presenter: LocationListPresenter

    @Before
    fun setUp() {
        presenter = LocationListPresenter(
                getAllLocationsUseCase,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )
    }

    @Test
    fun loadLocationList(){
        // Given
        val expected = listOf(
                location,
                location,
                location
        )
        given(getAllLocationsUseCase.get())
                .willReturn(Observable.just(expected))

        // When
        presenter.resume(view)

        // Then
        verify(view).updateState(LocationListView.State(expected))
    }
}