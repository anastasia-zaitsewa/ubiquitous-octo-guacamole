package sample.maps.repository

import io.reactivex.Observable
import sample.maps.model.Location
import sample.maps.model.realm.LocationRealm

/**
 * In memory implementation of [LocationRepository]
 */
class InMemoryLocationRepository : LocationRepository {

    private val storedLocations = emptyList<Location>().toMutableList()

    override fun getAll(): Observable<List<Location>> {
        return Observable.create<List<Location>> {
            storedLocations
        }
    }

    override fun add(location: Location) {
        if (isLocationNew(location)) {
            storedLocations.add(location)
        }
    }

    private fun isLocationNew(location: Location): Boolean {
        return storedLocations.find { location == it } == null
    }
}