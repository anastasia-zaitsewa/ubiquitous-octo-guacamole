package sample.maps.interactor

import android.Manifest
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

}