package sample.maps.interactor

import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.model.Location
import sample.maps.repository.LocationRepository

@RunWith(MockitoJUnitRunner::class)
class SaveLocationToRepositoryUseCaseTest {

    @Mock
    lateinit var locationRepository: LocationRepository

    @InjectMocks
    lateinit var useCase: SaveLocationToRepositoryUseCase

    @Test
    fun save() {
        // Given
        val expected = Location(10.0, 10.0)

        // When
        val observer = useCase.save(expected).test()

        // Then
        verify(locationRepository).add(expected)
        observer.assertNoErrors()
        observer.assertComplete()
    }
}