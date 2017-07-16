package sample.maps.persistence

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

/**
 * Provides last know location for current user.
 */
class LocationProvider @Inject constructor(val context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    init {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                location.offer(it)
            }
        }

    }

    val location: BehaviorProcessor<Location> = BehaviorProcessor.create<Location>()

    fun lastKnownLocation(): Observable<Location> {
        return location.toObservable()
    }
}