package sample.maps.repository

import io.reactivex.Observable
import io.realm.Realm
import sample.maps.model.Location
import sample.maps.model.realm.LocationRealm
import javax.inject.Inject

/**
 * Realm implementation of [LocationRepository]
 */
class RealmLocationRepository @Inject constructor(
        private val realm: Realm
) : LocationRepository {

    override fun getAll(): Observable<List<Location>> {
        return Observable.create<List<Location>> {
            realm.where(LocationRealm::class.java).findAll()
        }
    }

    override fun add(location: Location) {
        if(isLocationNew(location)) {
            realm.insert(LocationRealm(location.latitude, location.longitude))
        }
    }

    private fun isLocationNew(location: Location): Boolean {
        return realm.where(LocationRealm::class.java)
                .equalTo("latitude", location.latitude)
                .equalTo("longitude", location.longitude)
                .findAll().size == 0
    }
}