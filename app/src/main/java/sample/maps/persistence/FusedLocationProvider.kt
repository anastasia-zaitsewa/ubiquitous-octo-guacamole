package sample.maps.persistence

import android.app.Activity
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.Scheduler
import sample.maps.injection.annotation.UiScheduler
import javax.inject.Inject

/**
 * Provides last know location for current user using android fused location client.
 */
open class FusedLocationProvider @Inject constructor(private val activity: Activity,
                                                     @UiScheduler private val uiScheduler: Scheduler)
    : LocationProvider {

    /**
     * Provides last know location for current user as an observable.
     */
    override fun lastKnownLocation(): Observable<LocationProvider.Result> {
        return Observable
                .create<LocationProvider.Result>({ emitter ->
                    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

                    fusedLocationClient.lastLocation.addOnSuccessListener {
                        if (it != null) {
                            emitter.onNext(LocationProvider.Result.Success(it))
                        } else {
                            emitter.onNext(LocationProvider.Result.NoLocation)
                        }
                        emitter.onComplete()
                    }
                })
                .subscribeOn(uiScheduler)
    }
}