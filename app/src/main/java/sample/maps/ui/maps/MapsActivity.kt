package sample.maps.ui.maps

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.maps_activity.*
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerMapsActivityComponent
import sample.maps.injection.module.MapsActivityModule
import javax.inject.Inject

class MapsActivity : AppCompatActivity() {

    @Inject
    lateinit var rxPermissions: RxPermissions

    @Inject
    lateinit var mapsPresenter: MapsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        setContentView(R.layout.maps_activity)

        initComponents(savedInstanceState)

        rxPermissions.request(
                Manifest.permission.ACCESS_FINE_LOCATION
        ).subscribe()
    }

    private fun initComponents(savedInstanceState: Bundle?) {
        mapViewImpl.onCreate(savedInstanceState)
        mapViewImpl.setListener(mapsPresenter)
    }

    private fun initDagger() {
        DaggerMapsActivityComponent.builder()
                .activityModule(MapsActivityModule(this))
                .build()
                .injectMapsActivity(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.locationList -> mapsPresenter.locationListClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        mapViewImpl.onStart()
        mapsPresenter.resume(mapViewImpl)
    }

    override fun onResume() {
        super.onResume()
        mapViewImpl.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapViewImpl.onStop()
        mapsPresenter.pause()
    }

    override fun onPause() {
        super.onPause()
        mapViewImpl.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapViewImpl.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewImpl.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapViewImpl.onSaveInstanceState(outState)
    }
}
