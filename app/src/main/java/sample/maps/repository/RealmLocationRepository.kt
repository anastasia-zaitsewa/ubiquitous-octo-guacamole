package sample.maps.repository

import io.reactivex.Observable
import io.realm.Realm
import sample.maps.model.Location
import javax.inject.Inject

/**
 * Realm implementation of [LocationRepository]
 */
class RealmLocationRepository @Inject constructor(
        private val realm: Realm
) : LocationRepository {

    override fun getAll(): Observable<Location> {
        return Observable.create<Location> { realm.where(Location::class.java).findAll() }
    }

    override fun add(location: Location) {
        if(isLocationNew(location)) {
            realm.insert(location)
        }
    }

    private fun isLocationNew(location: Location): Boolean {
        return realm.where(Location::class.java)
                .equalTo("latitude", location.latitude)
                .equalTo("longitude", location.longitude)
                .findAll().size == 0
    }
}