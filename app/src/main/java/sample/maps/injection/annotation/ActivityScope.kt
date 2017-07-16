package sample.maps.injection.annotation

import javax.inject.Scope

/**
 * Scope for instances which live as long as the activity.
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
