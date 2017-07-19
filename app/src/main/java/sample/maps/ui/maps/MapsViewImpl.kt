package sample.maps.ui.maps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.LocaleList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.Flowables
import sample.maps.R
import sample.maps.ui.list.LocationListActivity

/**
 * Implementation of [MapsView]
 */
class MapsViewImpl(context: Context, attributeSet: AttributeSet)
    : MapsView, FrameLayout(context, attributeSet) {

    private var listener: MapsView.Listener? = null

    private var mapView: MapView

    private val googleMap = BehaviorProcessor.create<GoogleMap>()
    private val state = BehaviorProcessor.create<MapsView.State>()

    init {
        LayoutInflater
                .from(context)
                .inflate(R.layout.maps_view, this, true)

        mapView = (findViewById(R.id.map) as MapView)

        subscribeForStateUpdate()

        findViewById(R.id.add).setOnClickListener { listener?.addLocationClicked() }
    }


    private fun subscribeForStateUpdate() {
        Flowables.combineLatest(
                googleMap,
                state,
                { map, state -> map to state }
        ).subscribe { updateView(it) }
    }

    private fun updateView(pair: Pair<GoogleMap, MapsView.State>) {
        val map = pair.first
        pair.second.locationList
                .map { (latitude, longitude) ->
                    map.addMarker(MarkerOptions()
                            .position(LatLng(latitude, longitude))
                            .title("Saved Location"))
                }

    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onCreate(bundle: Bundle?) {
        mapView.onCreate(bundle)
        mapView.getMapAsync {
            if (it != null) {
                with(it) {
                    uiSettings.isMyLocationButtonEnabled = true
                    isMyLocationEnabled = true
                }

                googleMap.offer(it)
            }
        }
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onStart() {
        mapView.onStart()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onStop() {
        mapView.onStop()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onResume() {
        mapView.onResume()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onPause() {
        mapView.onPause()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onDestroy() {
        mapView.onDestroy()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onLowMemory() {
        mapView.onLowMemory()
    }

    fun onSaveInstanceState(bundle: Bundle?) {
        mapView.onSaveInstanceState(bundle)
    }

    override fun updateState(newState: MapsView.State) {
        state.offer(newState)
    }

    override fun setListener(listener: MapsView.Listener) {
        this.listener = listener
    }

    override fun navigateLocationList() {
        context.startActivity(
                Intent(context, LocationListActivity::class.java)
        )
    }

}