package sample.maps.interactor

import android.location.Location
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.repository.LocationRepository

@RunWith(MockitoJUnitRunner::class)
class SaveLocationToRepositoryUseCaseTest {

    private val dummyLatLang = 10.0

    @Mock
    lateinit var location: Location

    @Mock
    lateinit var locationRepository: LocationRepository

    @InjectMocks
    lateinit var useCase: SaveLocationToRepositoryUseCase

    @Test
    fun save() {
        // When
        val observer = useCase.save(location).test()

        // Then
        verify(locationRepository).add(location)
        observer.assertNoErrors()
        observer.assertComplete()
    }
}