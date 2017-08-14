package sample.maps.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.location_list_activity.*
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.module.LocationListActivityModule
import javax.inject.Inject

class LocationListActivity : AppCompatActivity() {
    @Inject
    lateinit var locationListPresenter: LocationListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        setContentView(R.layout.location_list_activity)

        initActionBar()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        locationListPresenter.resume(locationListView)
    }

    override fun onStop() {
        super.onStop()
        locationListPresenter.pause()
    }

    private fun initDagger() {
        MapsApplication
                .mainComponent
                .plus(LocationListActivityModule(this))
                .injectLocationListActivity(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}