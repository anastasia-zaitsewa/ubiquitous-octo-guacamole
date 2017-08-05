package sample.maps.ui.maps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.maps_view.view.*
import sample.maps.R
import sample.maps.ui.list.LocationListActivity

/**
 * Implementation of [MapsView]
 */
class MapsViewImpl @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MapsView, FrameLayout(context, attrs, defStyleAttr) {

    private var listener: MapsView.Listener? = null

    private val googleMap = BehaviorProcessor.create<GoogleMap>()
    private val state = BehaviorProcessor.create<MapsView.State>()

    init {
        LayoutInflater
                .from(context)
                .inflate(R.layout.maps_view, this, true)

        subscribeForStateUpdate()

        addButton.setOnClickListener { listener?.addLocationClicked() }
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
        googleMapView.onCreate(bundle)
        googleMapView.getMapAsync {
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
        googleMapView.onStart()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onStop() {
        googleMapView.onStop()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onResume() {
        googleMapView.onResume()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onPause() {
        googleMapView.onPause()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onDestroy() {
        googleMapView.onDestroy()
    }

    /**
     * Method to work with [MapView] lifecycle
     */
    fun onLowMemory() {
        googleMapView.onLowMemory()
    }

    fun onSaveInstanceState(bundle: Bundle?) {
        googleMapView.onSaveInstanceState(bundle)
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