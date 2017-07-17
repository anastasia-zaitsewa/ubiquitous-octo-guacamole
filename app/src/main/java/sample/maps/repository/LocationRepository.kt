package sample.maps.repository

import io.reactivex.Observable
import sample.maps.model.Location

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