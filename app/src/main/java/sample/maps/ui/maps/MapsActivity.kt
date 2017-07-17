package sample.maps.ui.maps

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerActivityComponent
import sample.maps.injection.module.ActivityModule
import sample.maps.persistence.LocationProvider
import javax.inject.Inject

class MapsActivity : FragmentActivity() {

    private lateinit var map: GoogleMap

    @Inject
    lateinit var locationProvider: LocationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        setContentView(R.layout.maps_activity)
    }

    private fun initDagger() {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .mainComponent(MapsApplication.mainComponent)
                .build()
                .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_menu, menu)
        return true
    }
}
