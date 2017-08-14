package sample.maps.injection.module

import dagger.Module
import dagger.Provides
import sample.maps.data.repository.InMemoryLocationRepository
import sample.maps.data.repository.LocationRepository
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