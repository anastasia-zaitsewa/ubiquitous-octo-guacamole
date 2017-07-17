package sample.maps.interactor

import io.reactivex.Completable
import sample.maps.model.Location
import sample.maps.repository.LocationRepository
import javax.inject.Inject

/**
 * Saves given location to repository.
 */
open class SaveLocationToRepositoryUseCase @Inject constructor(
        private val locationRepository: LocationRepository
) {
    /**
     * @param latLong - location to save
     */
    open fun save(location: Location): Completable {
        return Completable.fromAction {
            locationRepository.add(location)
        }
    }
}