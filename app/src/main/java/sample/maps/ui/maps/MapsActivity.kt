package sample.maps.ui.maps

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.tbruyelle.rxpermissions2.RxPermissions
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerActivityComponent
import sample.maps.injection.module.ActivityModule
import javax.inject.Inject

class MapsActivity : AppCompatActivity() {

    @Inject
    lateinit var rxPermissions: RxPermissions

    @Inject
    lateinit var mapsPresenter: MapsPresenter
    private lateinit var mapsView: MapsViewImpl

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
        mapsView = findViewById(R.id.map_view) as MapsViewImpl
        mapsView.onCreate(savedInstanceState)
        mapsView.setListener(mapsPresenter)

    }

    private fun initDagger() {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .mainComponent(MapsApplication.mainComponent)
                .build()
                .injectMapsActivity(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.list -> mapsPresenter.locationListClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        mapsView.onStart()
        mapsPresenter.resume(mapsView)
    }

    override fun onResume() {
        super.onResume()
        mapsView.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapsView.onStop()
        mapsPresenter.pause()
    }

    override fun onPause() {
        super.onPause()
        mapsView.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        mapsView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapsView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapsView.onSaveInstanceState(outState)
    }
}
