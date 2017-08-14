package sample.maps.injection.module

import android.app.Activity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.data.FusedLocationProvider
import sample.maps.data.LocationProvider

/**
 * Provides activity specific dependencies (or activity itself)
 */
@Module class ActivityModule(private val activity: Activity) {
    @Provides
    fun providesActivity(): Activity {
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