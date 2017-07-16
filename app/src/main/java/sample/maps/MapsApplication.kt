package sample.maps

import android.app.Application
import sample.maps.injection.component.DaggerMainComponent
import sample.maps.injection.component.MainComponent
import sample.maps.injection.module.AndroidModule

class MapsApplication : Application() {
    companion object {
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }
}