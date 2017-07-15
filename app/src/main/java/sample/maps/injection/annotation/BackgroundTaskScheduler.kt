package sample.maps.injection.annotation

import javax.inject.Qualifier

/**
 * Indicates that Scheduler is used for blocking operations
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class BackgroundTaskScheduler
