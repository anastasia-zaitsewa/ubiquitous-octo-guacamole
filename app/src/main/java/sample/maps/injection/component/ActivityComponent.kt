package sample.maps.injection.component

import dagger.Component
import sample.maps.injection.annotation.ActivityScope
import sample.maps.injection.module.ActivityModule
import sample.maps.ui.list.LocationListActivity
import sample.maps.ui.maps.MapsActivity

/**
 * Activity scoped component
 */
@ActivityScope
@Component(
        dependencies = arrayOf(MainComponent::class),
        modules = arrayOf(ActivityModule::class)
) interface ActivityComponent {

    fun injectMapsActivity(mapsActivity: MapsActivity)

    fun injectLocationListActivity(locationListActivity: LocationListActivity)
}