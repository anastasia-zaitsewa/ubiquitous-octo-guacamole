package sample.maps.ui.list

import android.location.Location

/**
 * Shows list with all saved locations
 */
interface LocationListView {

    fun updateState(newState: State)

    data class State(val locationList : List<Location>)
}