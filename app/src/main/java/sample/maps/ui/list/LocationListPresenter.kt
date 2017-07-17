package sample.maps.ui.list

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import sample.maps.injection.annotation.BackgroundTaskScheduler
import sample.maps.injection.annotation.UiScheduler
import sample.maps.interactor.GetAllLocationsFromRepositoryUseCase
import javax.inject.Inject

/**
 * Presenter for [LocationListView]
 */
open class LocationListPresenter @Inject constructor(
        private val getAllLocationsFromRepositoryUseCase: GetAllLocationsFromRepositoryUseCase,
        @BackgroundTaskScheduler private val backgroundScheduler: Scheduler,
        @UiScheduler private val uiScheduler: Scheduler) {

    private var view: LocationListView? = null
    private val compositeSubscription = CompositeDisposable()

    open fun resume(view: LocationListView) {
        this.view = view
        subscribeForState(view)
    }

    fun pause() {
        compositeSubscription.clear()
        view = null
    }

    private fun subscribeForState(view: LocationListView) {
        compositeSubscription.add(
                getAllLocationsFromRepositoryUseCase.get()
                        .map { LocationListView.State(it) }
                        .subscribeOn(backgroundScheduler)
                        .observeOn(uiScheduler)
                        .subscribe { view.updateState(it) }
        )
    }
}
