package sample.maps.injection.module

import android.app.Activity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

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
}