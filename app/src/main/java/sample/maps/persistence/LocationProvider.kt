package sample.maps.persistence

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

/**
 * Provides last know location for current user.
 */
open class LocationProvider @Inject constructor(context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    init {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            location.offer(it)
        }

    }

    private val location = BehaviorProcessor.create<Location?>()

    open fun lastKnownLocation(): Observable<Location?> {
        return location.toObservable()
    }
}