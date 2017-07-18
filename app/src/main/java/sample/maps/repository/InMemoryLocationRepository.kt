package sample.maps.repository

import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import sample.maps.event.Signal
import sample.maps.model.Location
import javax.inject.Singleton

/**
 * In memory implementation of [LocationRepository]
 */
@Singleton
class InMemoryLocationRepository : LocationRepository {

    private val storedLocations = arrayListOf<Location>()
    private val updates = BehaviorProcessor.create<Signal>()

    override fun getAll(): Observable<List<Location>> {
        return updates.toObservable().map { _ -> storedLocations}
    }

    override fun add(location: Location) {
        if (isLocationNew(location)) {
            storedLocations.add(location)
            updates.offer(Signal)
        }
    }

    private fun isLocationNew(location: Location): Boolean {
        return storedLocations.find { location == it } == null
    }
}