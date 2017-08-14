package sample.maps.data

import android.location.Location
import io.reactivex.Observable

/**
 * Provides last know location.
 */
interface LocationProvider {
    /**
     * Provides last know location.
     */
    fun lastKnownLocation(): Observable<Result>

    /**
     * Represents all possible results from last location request.
     */
    sealed class Result {
        data class Success(val location: Location) : Result()
        object NoLocation : Result()
    }
}