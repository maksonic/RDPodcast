package ru.maksonic.rdpodcast.navigation.api

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

/**
 * @Author: maksonic on 06.02.2022
 */
private const val PASSED_DATA = "passedData"

fun Fragment.navigate(actionId: Int, hostId: Int? = null, data: Parcelable? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }
    val bundle = Bundle().apply { putParcelable(PASSED_DATA, data) }
    val action = navController.currentDestination?.getAction(actionId)

    if (action != null && navController.currentDestination?.id != action.destinationId) {
        navController.navigate(actionId, bundle)
    }
}
fun NavController.navi(actionId: Int, hostId: Int? = null, data: Parcelable? = null) {

    val bundle = Bundle().apply { putParcelable(PASSED_DATA, data) }
    val action = this.currentDestination?.getAction(actionId)

    if (action != null && this.currentDestination?.id != action.destinationId) {
        this.navigate(actionId, bundle)
    }
}

val Fragment.navigationData: Parcelable?
    get() = arguments?.getParcelable(PASSED_DATA)

