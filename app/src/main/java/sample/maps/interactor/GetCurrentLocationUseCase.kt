package sample.maps.interactor

import android.Manifest
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.Observable.just
import sample.maps.model.Location
import sample.maps.persistence.LocationProvider
import javax.inject.Inject

/**
 * Returns current current user location.
 */
open class GetCurrentLocationUseCase @Inject constructor(
        private val locationProvider: LocationProvider,
        private val rxPermissions: RxPermissions
) {

    open fun get(): Observable<Location?> {
        if (rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return locationProvider.lastKnownLocation()
                    .map {
                        if (it != null) {
                            return@map Location(it.latitude, it.longitude)
                        } else {
                            return@map null
                        }
                    }
        } else {
            return just(null)
        }
    }
}
