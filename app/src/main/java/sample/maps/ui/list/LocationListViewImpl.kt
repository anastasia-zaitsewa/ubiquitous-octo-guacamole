package sample.maps.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import sample.maps.R

/**
 * Implementation of [LocationListView]
 */
class LocationListViewImpl(context: Context, attributeSet: AttributeSet)
    : LocationListView, FrameLayout(context, attributeSet) {

    private val itemsAdapter = LocationsAdapter(context)

    override fun updateState(newState: LocationListView.State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private inner class  LocationsAdapter(context: Context) : RecyclerView.Adapter<LocationViewHolder> () {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LocationViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: LocationViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        val inflater: LayoutInflater = LayoutInflater.from(context)


    }

    private inner class LocationViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView?.findViewById(R.id.label) as TextView
    }
}