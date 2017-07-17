package sample.maps.ui.maps

import sample.maps.model.Location

/**
 * Shows maps with current location
 */
interface MapsView {

    /**
     * Updates state of the view.
     *
     * @param newState new state of the view.
     */
    fun updateState(newState: State)

    /**
     * @param listener notified about user interaction.
     */
    fun setListener(listener: Listener)

    /**
     * Navigates to another screen, which contains locations saved, by user.
     */
    fun navigateLocationList()

    /**
     * Notified about user interaction.
     */
    interface Listener {
        /**
         * User wants to add current location.
         */
        fun addLocationClicked(location: Location)

        /**
         * Navigate to list with saved locations.
         */
        fun locationListClicked()
    }

    data class State(val location: Location)
}