package sample.maps.injection.component

import android.app.Application
import dagger.Component
import io.reactivex.Scheduler
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.injection.module.AndroidModule
import sample.maps.injection.module.MainModule
import sample.maps.data.repository.LocationRepository
import sample.maps.injection.module.LocationListActivityModule
import sample.maps.injection.module.MapsActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidModule::class,
        MainModule::class)) interface MainComponent {

    fun application(): Application

    fun locationRepository(): LocationRepository

    @BackgroundTaskScheduler
    fun backgroundScheduler(): Scheduler

    @UiScheduler
    fun uiScheduler(): Scheduler

    fun plus(mapsActivityModule: MapsActivityModule)

    fun plus(listActivityModule: LocationListActivityModule)
}