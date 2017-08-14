package sample.maps.injection.component

import dagger.Subcomponent
import sample.maps.injection.annotation.ActivityScope
import sample.maps.injection.module.LocationListActivityModule
import sample.maps.ui.list.LocationListActivity

/**
 * Activity scoped component
 */
@ActivityScope
@Subcomponent(
        modules = arrayOf(LocationListActivityModule::class)
) interface LocationListActivityComponent {

    fun injectLocationListActivity(locationListActivity: LocationListActivity)
}