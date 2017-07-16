package sample.maps.interactor

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Saves given location to repository.
 */
open class SaveLocationToRepositoryUseCase @Inject constructor(){
    /**
     * @param latLong - location to save
     */
    open fun save(latLong: LatLng): Completable {
        return Completable.complete()
    }
}