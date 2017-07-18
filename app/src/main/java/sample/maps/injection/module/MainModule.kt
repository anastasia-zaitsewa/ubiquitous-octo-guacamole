package sample.maps.injection.module

import dagger.Module
import dagger.Provides
import sample.maps.repository.InMemoryLocationRepository
import sample.maps.repository.LocationRepository
import javax.inject.Singleton

/**
 * Contains application scoped dependencies
 */
@Module class MainModule {
    @Provides
    @Singleton
    fun providesLocationRepository(): LocationRepository {
        return InMemoryLocationRepository()
    }
}