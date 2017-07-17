package sample.maps.ui.maps

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.Flowables
import sample.maps.R

/**
 * Implementation of [MapsView]
 */
class MapsViewImpl(context: Context) : MapsView, FrameLayout(context) {

    private var listener: MapsView.Listener? = null

    private val googleMap = BehaviorProcessor.create<GoogleMap>()
    private val state = BehaviorProcessor.create<MapsView.State>()

    init {
        LayoutInflater
                .from(context)
                .inflate(R.layout.maps_view, this, true)

        (findViewById(R.id.map) as MapView).getMapAsync {
            if (it != null) {
                googleMap.offer(it)
            }
        }

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
        val location = pair.second.location

        map.addMarker(MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                .title("Current Location"))

        map.moveCamera(
                CameraUpdateFactory.newLatLng(LatLng(
                        location.latitude,
                        location.longitude
                ))
        )
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