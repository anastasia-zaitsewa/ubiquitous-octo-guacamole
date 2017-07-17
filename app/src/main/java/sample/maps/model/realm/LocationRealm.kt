package sample.maps.model.realm

import io.realm.RealmObject

/**
 * Represents location as a combination of latitude-longitude.
 * Fields are mutable because of Realm.
 */
open class LocationRealm(var latitude: Double = 0.0, var longitude: Double = 0.0) : RealmObject()