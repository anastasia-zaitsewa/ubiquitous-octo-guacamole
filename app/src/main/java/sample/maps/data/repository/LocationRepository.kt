package sample.maps.data.repository

import android.location.Location
import io.reactivex.Observable

/**
 * Provides read and write access to user locations
 */
interface LocationRepository {
    /**
     * Retrieves all stored locations
     */
    fun getAll(): Observable<List<Location>>

    /**
     * Saves given location to DB
     */
    fun add(location: Location)
}