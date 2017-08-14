package sample.maps.injection.module

import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import sample.maps.data.FusedLocationProvider
import sample.maps.data.LocationProvider
import sample.maps.injection.annotation.UiScheduler
import sample.maps.ui.maps.MapsActivity

/**
 * Provides activity specific dependencies (or activity itself)
 */
@Module class MapsActivityModule(private val activity: MapsActivity) {
    @Provides
    fun providesActivity(): MapsActivity {
        return activity
    }

    @Provides
    fun providesRxPermissions(): RxPermissions {
        return RxPermissions(activity)
    }

    @Provides
    fun providesLocationProvider(@UiScheduler uiScheduler: Scheduler): LocationProvider {
        return FusedLocationProvider(activity, uiScheduler)
    }
}