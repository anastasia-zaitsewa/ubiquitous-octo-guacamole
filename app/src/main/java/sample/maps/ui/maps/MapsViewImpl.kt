package sample.maps.ui.maps

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.processors.BehaviorProcessor
import sample.maps.R
import sample.maps.model.Location

/**
 * Implementation of [MapsView]
 */
class MapsViewImpl(context: Context) : MapsView, FrameLayout(context) {

    private var listener: MapsView.Listener? = null

    private val compositeSubscription = CompositeDisposable()
    private val googleMap: BehaviorProcessor<GoogleMap> = BehaviorProcessor.create()
    private val state: BehaviorProcessor<MapsView.State> = BehaviorProcessor.create()

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

        // TODO: Fix me
        findViewById(R.id.add).setOnClickListener { listener?.addLocationClicked(Location(0.0, 0.0)) }
    }

    private fun subscribeForStateUpdate() {
        // TODO: fix me
//        Flowable.combineLatest(
//                googleMap,
//                state,
//                BiFunction { map: GoogleMap?, location: Location ->
//                    {
//                        updateView(map, location)
//                    }
//                }
//        )
    }

    private fun updateView(map: GoogleMap?, location: Location): Unit? {
        return map?.let {
            it.addMarker(MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title("Current Location"))

            it.moveCamera(
                    CameraUpdateFactory.newLatLng(LatLng(
                            location.latitude,
                            location.longitude
                    ))
            )
        }
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