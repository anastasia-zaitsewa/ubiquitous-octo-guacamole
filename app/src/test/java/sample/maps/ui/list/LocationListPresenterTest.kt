package sample.maps.ui.list

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
import sample.maps.model.Location

@RunWith(MockitoJUnitRunner::class)
class LocationListPresenterTest {

    val expected = listOf(
            Location(1.0, 1.0),
            Location(2.0, 2.0),
            Location(3.0, 3.0)
    )

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

        given(getAllLocationsUseCase.get())
                .willReturn(Observable.just(expected))
    }

    @Test
    fun loadLocationList(){
        // When
        presenter.resume(view)

        // Then
        verify(view).updateState(LocationListView.State(expected))
    }
}