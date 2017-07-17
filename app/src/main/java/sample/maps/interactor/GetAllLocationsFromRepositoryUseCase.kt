package sample.maps.interactor

import io.reactivex.Observable
import sample.maps.model.Location
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
