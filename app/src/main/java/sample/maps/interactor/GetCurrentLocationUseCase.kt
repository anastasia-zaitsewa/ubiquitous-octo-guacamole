package sample.maps.interactor

import android.Manifest
import android.location.Location
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import sample.maps.persistence.LocationProvider
import javax.inject.Inject

/**
 * Returns current current user location.
 */
open class GetCurrentLocationUseCase @Inject constructor(
        private val locationProvider: LocationProvider,
        private val rxPermissions: RxPermissions
) {

    open fun get(): Observable<Location> {
        return rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .filter { it }
                .switchMap { locationProvider.lastKnownLocation() }
    }
}
