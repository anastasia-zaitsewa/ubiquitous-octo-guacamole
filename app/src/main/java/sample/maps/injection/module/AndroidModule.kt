package sample.maps.injection.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import javax.inject.Singleton

/**
 * Contains android related dependencies.
 */
@Module class AndroidModule (private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }
    
    @Provides
    @Singleton
    fun providesResources(): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    @BackgroundTaskScheduler
    fun providesBackgroundScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @UiScheduler
    fun providesUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
