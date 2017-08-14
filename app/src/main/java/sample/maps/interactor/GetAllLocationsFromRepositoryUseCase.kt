package sample.maps.interactor

import android.location.Location
import io.reactivex.Observable
import sample.maps.repository.LocationRepository
import javax.inject.Inject

/**
 * Retrieves all saved by user locations
 */
open class GetAllLocationsFromRepositoryUseCase @Inject constructor(
        private val locationRepository: LocationRepository
) {
    open fun get(): Observable<List<Location>> {
        return locationRepository.getAll()
    }
}
