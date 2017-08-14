package sample.maps.injection.component

import dagger.Subcomponent
import sample.maps.injection.annotation.ActivityScope
import sample.maps.injection.module.MapsActivityModule
import sample.maps.ui.maps.MapsActivity

/**
 * Activity scoped component
 */
@ActivityScope
@Subcomponent(
        modules = arrayOf(MapsActivityModule::class)
) interface MapsActivityComponent {

    fun injectMapsActivity(mapsActivity: MapsActivity)
}