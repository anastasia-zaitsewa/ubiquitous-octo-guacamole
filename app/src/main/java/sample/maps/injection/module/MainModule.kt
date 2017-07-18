package sample.maps.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import sample.maps.repository.LocationRepository
import sample.maps.repository.InMemoryLocationRepository
import javax.inject.Singleton

/**
 * Contains application scoped dependencies
 */
@Module class MainModule {
    @Provides
    @Singleton
    fun providesRealm(context: Context): Realm {
        Realm.init(context)
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    fun providesLocationRepository(): LocationRepository {
        return InMemoryLocationRepository()
    }
}