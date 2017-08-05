package sample.maps.ui.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.location_list_view.view.*
import sample.maps.R
import sample.maps.model.Location

/**
 * Implementation of [LocationListView]
 */
class LocationListViewImpl(context: Context, attributeSet: AttributeSet)
    : LocationListView, FrameLayout(context, attributeSet) {

    private val itemsAdapter = LocationsAdapter(context)

    init {
        LayoutInflater
                .from(context)
                .inflate(R.layout.location_list_view, this, true)

        locationList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = itemsAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun updateState(newState: LocationListView.State) {
        itemsAdapter.setItems(newState.locationList)
    }

    /**
     * Adapter for for [RecyclerView] with location items
     */
    private inner class LocationsAdapter(context: Context) : RecyclerView.Adapter<LocationViewHolder>() {

        private var items: List<Location> = emptyList()

        val inflater: LayoutInflater = LayoutInflater.from(context)

        init {
            setHasStableIds(true)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LocationViewHolder {
            return LocationViewHolder(inflater.inflate(R.layout.location_list_item, parent, false))
        }

        override fun onBindViewHolder(holder: LocationViewHolder?, position: Int) {
            if (position in 0 until items.size) {
                holder?.setViewModel(items[position])
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun getItemId(position: Int): Long {
            return items[position].hashCode().toLong()
        }

        fun setItems(items: List<Location>) {
            this.items = items
            notifyDataSetChanged()
        }


    }

    private inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val label = itemView.findViewById(R.id.locationLabel) as TextView

        fun setViewModel(model: Location) {
            label.text = model.toString()
        }
    }
}