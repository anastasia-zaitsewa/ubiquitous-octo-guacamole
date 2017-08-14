package sample.maps.repository

import android.location.Location
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Singleton

/**
 * In memory implementation of [LocationRepository]
 */
@Singleton
class InMemoryLocationRepository : LocationRepository {

    private val storedLocations = arrayListOf(
            createLocation(53.5434774, 9.9745385),
            createLocation(53.548148, 9.975436),
            createLocation(53.5547652, 9.970832),
            createLocation(53.5536913, 9.9670254),
            createLocation(53.550823, 9.981951),
            createLocation(53.5651967, 9.9701628),
            createLocation(53.5651967, 9.9701628),
            createLocation(53.5651967, 9.9701628),
            createLocation(53.5717677, 9.988324),
            createLocation(53.5892213, 9.9829051),
            createLocation(53.6110451, 9.9955775),
            createLocation(53.6322849, 9.9693622)
    )

    private fun createLocation(latitude: Double, longitude: Double): Location {
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    private val updates = BehaviorProcessor.createDefault(Unit)

    override fun getAll(): Observable<List<Location>> {
        return updates.toObservable().map { _ -> storedLocations }
    }

    override fun add(location: Location) {
        if (isLocationNew(location)) {
            storedLocations.add(location)
            updates.offer(Unit)
        }
    }

    private fun isLocationNew(location: Location): Boolean {
        return storedLocations.find {
            location.longitude == it.longitude
                    && location.latitude == it.latitude
        } == null
    }
}