package sample.maps.interactor

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import sample.maps.model.Location
import sample.maps.repository.LocationRepository
import javax.inject.Inject

/**
 * Saves given location to repository.
 */
open class SaveLocationToRepositoryUseCase @Inject constructor(
        private val locationRepository: LocationRepository
){
    /**
     * @param latLong - location to save
     */
    open fun save(latLong: LatLng): Completable {
        return Completable.fromAction {
            locationRepository.add(Location(latLong.latitude, latLong.longitude))
        }
    }
}