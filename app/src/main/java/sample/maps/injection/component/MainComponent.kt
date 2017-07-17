package sample.maps.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import io.reactivex.Scheduler
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.injection.module.AndroidModule
import sample.maps.injection.module.MainModule
import sample.maps.repository.LocationRepository
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, MainModule::class)) interface MainComponent {

    fun application(): Application

    fun context(): Context

    fun locationRepository(): LocationRepository

    @BackgroundTaskScheduler
    fun backgroundScheduler(): Scheduler

    @UiScheduler
    fun uiScheduler(): Scheduler
}