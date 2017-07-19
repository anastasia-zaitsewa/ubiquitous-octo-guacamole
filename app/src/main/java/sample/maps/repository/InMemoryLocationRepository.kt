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

    private val storedLocations = arrayListOf<Location>(
            Location(53.5434774, 9.9745385),
            Location(53.548148, 9.975436),
            Location(53.5547652, 9.970832),
            Location(53.5536913, 9.9670254),
            Location(53.550823, 9.981951),
            Location(53.5651967, 9.9701628),
            Location(53.5651967, 9.9701628),
            Location(53.5717677, 9.988324),
            Location(53.5892213, 9.9829051),
            Location(53.6110451, 9.9955775),
            Location(53.6322849, 9.9693622)
    )
    private val updates = BehaviorProcessor.createDefault(Signal)

    override fun getAll(): Observable<List<Location>> {
        return updates.toObservable().map { _ -> storedLocations }
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