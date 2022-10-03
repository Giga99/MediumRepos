package com.medium.navigation.details

import androidx.lifecycle.ViewModel
import com.medium.navigation.navigation_utils.AppNavigator
import com.medium.navigation.navigation_utils.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    fun onNavigateToCurrentUserDetailsButtonClicked() {
        appNavigator.tryNavigateTo(
            Destination.UserDetailsScreen(
                fistName = "CurrentUserFirstName",
                lastName = "CurrentUserLastName"
            )
        )
    }
}
