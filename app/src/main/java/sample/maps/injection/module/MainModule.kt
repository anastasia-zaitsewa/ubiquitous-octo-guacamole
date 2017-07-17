package sample.maps.injection.module

import dagger.Module
import dagger.Provides
import io.realm.Realm
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
}