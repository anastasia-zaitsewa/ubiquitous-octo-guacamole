package sample.maps.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.maps.MapsApplication
import sample.maps.R
import sample.maps.injection.component.DaggerActivityComponent
import sample.maps.injection.module.ActivityModule
import javax.inject.Inject

class LocationListActivity : AppCompatActivity() {
    @Inject
    lateinit var locationListPresenter: LocationListPresenter
    private lateinit var locationListView: LocationListViewImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()

        setContentView(R.layout.location_list_activity)
        
        initComponents()
    }

    private fun initComponents() {
        locationListView = findViewById(R.id.list_view) as LocationListViewImpl
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
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .mainComponent(MapsApplication.mainComponent)
                .build()
                .injectLocationListActivity(this)
    }
}