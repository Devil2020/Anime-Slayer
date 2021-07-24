package com.morse.common.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.*

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if ((action != null && currentDestination?.id != action.destinationId) || currentDestination?.id != graph[resId].id /*to handle case in which the resId is destination itself not an action Id*/) {
        navigate(resId, args, navOptions, navExtras)
    }
}


fun NavController.navigateSafeWithNavDirections(
    navDirections: NavDirections
) {
    navigateSafe(navDirections.actionId, navDirections.arguments )
}


fun ActivityNavigator.navigateToActivity(
    currentActivity: Activity,
    destinationActivity: Class<*>,
    isClosedAfterOpen: Boolean = true,
    argsBundle: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    this.navigate(
        this.createDestination()
            .setIntent(Intent(currentActivity, destinationActivity)),
        argsBundle,
        navOptions,
        navigatorExtras
    )
    with(isClosedAfterOpen) {
        if (this) {
            currentActivity.finish()
        }
    }
}

fun ActivityNavigator.navigateToActivityInStack(
    currentActivity: Activity,
    destinationActivity: Class<*>,
    isClosedAfterOpen: Boolean = true,
    argsBundle: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    this.navigate(
        this.createDestination()
            .setIntent(Intent(currentActivity, destinationActivity).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }),
        argsBundle,
        navOptions,
        navigatorExtras
    )
    with(isClosedAfterOpen) {
        if (this) {
            currentActivity.finish()
        }
    }
}

