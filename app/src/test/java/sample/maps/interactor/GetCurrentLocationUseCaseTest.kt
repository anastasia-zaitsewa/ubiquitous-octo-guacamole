package sample.maps.interactor

import android.Manifest
import android.location.Location
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable.just
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import sample.maps.data.LocationProvider
import sample.maps.interactor.GetCurrentLocationUseCase.Result.Failure
import sample.maps.interactor.GetCurrentLocationUseCase.Result.Success


@RunWith(MockitoJUnitRunner::class)
class GetCurrentLocationUseCaseTest {

    @Mock
    lateinit var location: Location
    @Mock
    lateinit var locationProvider: LocationProvider
    @Mock
    lateinit var rxPermissions: RxPermissions
    @InjectMocks
    lateinit var useCase: GetCurrentLocationUseCase

    @Before
    fun setUp(){
        given(rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION))
                .willReturn(true)
        given(locationProvider.lastKnownLocation())
                .willReturn(just(LocationProvider.Result.Success(location)))
    }

    @Test
    fun get_permissionGranted() {
        // When
        useCase.get().test()
                //Then
                .assertResult(Success(location))
    }

    @Test
    fun get_permissionNotGranted() {
        // Given
        given(rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION))
                .willReturn(false)

        // When
        val observer = useCase.get().test()

        // Then
        verifyZeroInteractions(locationProvider)
        observer.assertValue(Failure)
        observer.assertComplete()
    }

    @Test
    fun get_noLocationAvailable() {
        // Given
        given(locationProvider.lastKnownLocation())
                .willReturn(just(LocationProvider.Result.NoLocation))

        // When
        useCase.get().test()
                //Then
                .assertResult(Failure)
    }
}