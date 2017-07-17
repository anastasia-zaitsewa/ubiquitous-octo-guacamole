package sample.maps.ui.maps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerActivityComponent
import sample.maps.injection.module.ActivityModule
import javax.inject.Inject

class MapsActivity : AppCompatActivity() {

    @Inject
    lateinit var mapsPresenter: MapsPresenter
    private lateinit var mapsView: MapsViewImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        setContentView(R.layout.maps_activity)

        initComponents()
    }

    private fun initComponents() {
        mapsView = findViewById(R.id.map) as MapsViewImpl
        mapsView.setListener(mapsPresenter)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.list -> mapsPresenter.locationListClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        mapsPresenter.resume(mapsView)
    }

    override fun onStop() {
        super.onStop()

        mapsPresenter.pause()
    }
}
