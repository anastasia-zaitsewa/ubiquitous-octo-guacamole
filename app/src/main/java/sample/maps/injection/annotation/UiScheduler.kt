package sample.maps.injection.annotation

import javax.inject.Qualifier

/**
 * Indicates that Scheduler is used for UI operations
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class UiScheduler
