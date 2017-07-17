package sample.maps.interactor

import com.nhaarman.mockito_kotlin.given
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.model.Location
import sample.maps.repository.LocationRepository

@RunWith(MockitoJUnitRunner::class)
class GetAllLocationsFromRepositoryUseCaseTest {

    @Mock
    lateinit var locationRepository: LocationRepository

    @InjectMocks
    lateinit var useCase: GetAllLocationsFromRepositoryUseCase

    @Test
    fun get() {
        // Given
        val expected = listOf(
                Location(1.0, 1.0),
                Location(2.0, 2.0),
                Location(3.0, 3.0)
        )
        given(locationRepository.getAll())
                .willReturn(Observable.just(expected))

        // When
        val observer = useCase.get().test()

        // Then
        observer.assertNoErrors()
        observer.assertValue { it == expected }
        observer.assertComplete()
    }
}