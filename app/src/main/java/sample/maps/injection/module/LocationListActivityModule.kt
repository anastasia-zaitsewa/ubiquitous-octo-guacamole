package sample.maps.injection.module

import android.app.Activity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.data.FusedLocationProvider
import sample.maps.data.LocationProvider
import sample.maps.injection.annotation.ActivityScope

/**
 * Provides activity specific dependencies (or activity itself)
 */
@Module class LocationListActivityModule(private val activity: Activity) {
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}