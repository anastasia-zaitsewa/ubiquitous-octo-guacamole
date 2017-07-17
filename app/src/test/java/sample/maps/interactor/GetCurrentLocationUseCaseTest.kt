package sample.maps.interactor

import android.Manifest
import android.location.Location
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable.just
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.persistence.LocationProvider


@RunWith(MockitoJUnitRunner::class)
class GetCurrentLocationUseCaseTest {

    private val dummyLatLang = 10.0

    @Mock
    lateinit var location: Location
    @Mock
    lateinit var locationProvider: LocationProvider
    @Mock
    lateinit var rxPermissions: RxPermissions
    @InjectMocks
    lateinit var useCase: GetCurrentLocationUseCase

    @Test
    fun get_permissionNotGranted() {
        // Given
        given(rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION))
                .willReturn(just(false))

        // When
        val observer = useCase.get().test()

        // Then
        verifyZeroInteractions(locationProvider)
        observer.assertNoValues()
        observer.assertComplete()
    }

    @Test
    fun get_permissionGranted() {
        // Given
        given(location.longitude)
                .willReturn(dummyLatLang)
        given(location.latitude)
                .willReturn(dummyLatLang)
        given(rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION))
                .willReturn(just(true))
        given(locationProvider.lastKnownLocation())
                .willReturn(just(location))

        // When
        val observer = useCase.get().test()

        // Then
        observer.assertValue(
                sample.maps.model.Location(10.0, 10.0)
        )
        observer.assertComplete()
    }
}