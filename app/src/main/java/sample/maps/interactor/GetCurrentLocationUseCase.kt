package sample.maps.interactor

import android.Manifest
import android.location.Location
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.Observable.just
import sample.maps.interactor.GetCurrentLocationUseCase.Result.*
import sample.maps.data.LocationProvider
import javax.inject.Inject

/**
 * Returns current current user location.
 */
open class GetCurrentLocationUseCase @Inject constructor(
        private val locationProvider: LocationProvider,
        private val rxPermissions: RxPermissions
) {

    open fun get(): Observable<Result> {
        if (rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return locationProvider.lastKnownLocation()
                    .map {
                        when(it){
                            is LocationProvider.Result.Success -> Success(it.location)
                            is LocationProvider.Result.NoLocation -> Failure
                        }
                    }
        } else {
            return just(Failure)
        }
    }

    sealed class Result {

        data class Success(val location: Location) : Result()

        object Failure : Result()

    }

}
