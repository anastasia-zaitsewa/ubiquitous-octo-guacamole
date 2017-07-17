package sample.maps.injection.module

import dagger.Module
import dagger.Provides
import io.realm.Realm
import sample.maps.repository.LocationRepository
import sample.maps.repository.RealmLocationRepository
import javax.inject.Singleton

/**
 * Contains application scoped dependencies
 */
@Module class MainModule {
    @Provides
    @Singleton
    fun providesRealm() : Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    fun provideslocationRepository(realm: Realm) : LocationRepository {
        return RealmLocationRepository(realm)
    }
}