package sample.maps.interactor

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by biovamp on 16/07/2017.
 */
open class SaveLocationToRepositoryUseCase @Inject constructor(){
    open fun save(latLong: LatLng): Completable {
        return Completable.complete()
    }
}