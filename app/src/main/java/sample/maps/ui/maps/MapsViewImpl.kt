package sample.maps.ui.maps

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.Flowables
import sample.maps.R

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
        val location = pair.second.location ?: return

//        map.addMarker(MarkerOptions()
//                .position(LatLng(location.latitude, location.longitude))
//                .title("Current Location"))
//
//        map.moveCamera(
//                CameraUpdateFactory.newLatLng(LatLng(
//                        location.latitude,
//                        location.longitude
//                ))
//        )
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
    fun onResume() {
        if (mapView.isAttachedToWindow) {
            mapView.onResume()
        }
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}