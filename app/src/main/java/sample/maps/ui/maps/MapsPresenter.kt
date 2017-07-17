package sample.maps.ui.maps

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.interactor.GetCurrentLocationUseCase
import sample.maps.interactor.SaveLocationToRepositoryUseCase
import sample.maps.model.Location
import sample.maps.ui.maps.MapsView.State
import javax.inject.Inject

/**
 * Presenter for [MapsView]
 */
open class MapsPresenter @Inject constructor(
        private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
        private val saveLocationUseCase: SaveLocationToRepositoryUseCase,
        @BackgroundTaskScheduler private val backgroundScheduler: Scheduler,
        @UiScheduler private val uiScheduler: Scheduler
) : MapsView.Listener {

    private var view: MapsView? = null
    private val compositeSubscription = CompositeDisposable()


    open fun resume(view: MapsView) {
        view.setListener(this)
        this.view = view
        subscribeForState(view)
    }

    fun pause() {
        compositeSubscription.clear()
        view = null
    }

    override fun addLocationClicked(location: Location) {
        saveLocationUseCase.save(location)
                .subscribeOn(backgroundScheduler)
                .subscribe()
    }

    override fun locationListClicked() {
        view?.navigateLocationList()
    }

    private fun subscribeForState(view: MapsView) {
        compositeSubscription.add(
                getCurrentLocationUseCase.get()
                        .map { buildState(it) }
                        .subscribeOn(backgroundScheduler)
                        .observeOn(uiScheduler)
                        .subscribe { view.updateState(it) }
        )
    }

    private fun buildState(location: Location): State {
        return State(location)
    }
}