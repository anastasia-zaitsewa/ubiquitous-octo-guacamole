package sample.maps.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import io.reactivex.Scheduler
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.injection.module.AndroidModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class)) interface MainComponent {

    fun application(): Application

    fun context(): Context

    @BackgroundTaskScheduler
    fun backgroundScheduler(): Scheduler

    @UiScheduler
    fun uiScheduler(): Scheduler
}