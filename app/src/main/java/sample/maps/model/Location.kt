package sample.maps.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Represents location as a combination of latitude-longitude.
 * Fields are mutable because of Realm.
 */
@RealmClass
open class Location(var latitude: Double = 0.0, var longitude: Double = 0.0) : RealmModel