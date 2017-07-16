package sample.maps.ui.maps

import android.location.Location
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.schedulers.Schedulers
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerActivityComponent
import sample.maps.injection.module.ActivityModule
import sample.maps.persistence.LocationProvider
import javax.inject.Inject

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    @Inject
    lateinit var locationProvider: LocationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        locationProvider.lastKnownLocation().subscribeOn(
                Schedulers.io()
        ).subscribe {
            val latLng = LatLng(it.latitude, it.longitude)

            with(map) {

                addMarker(MarkerOptions()
                        .position(latLng)
                        .title("Current Location"))

                moveCamera(
                        CameraUpdateFactory.newLatLng(latLng)
                )
            }
        }

        setContentView(R.layout.maps_activity)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}
