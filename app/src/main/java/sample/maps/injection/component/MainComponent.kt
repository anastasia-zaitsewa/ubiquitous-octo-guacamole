package sample.maps.injection.component

import dagger.Component
import sample.maps.MainActivity
import sample.maps.injection.module.AndroidModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidModule::class
)) interface MainComponent {
    fun inject(mainActivity: MainActivity)
}