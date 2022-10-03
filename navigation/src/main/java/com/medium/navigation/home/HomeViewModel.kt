package com.medium.navigation.home

import androidx.lifecycle.ViewModel
import com.medium.navigation.navigation_utils.Destination
import com.medium.navigation.navigation_utils.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToUsersButtonClicked() {
        appNavigator.tryNavigateTo(Destination.UsersScreen())
    }

    fun onNavigateToMessagesButtonClicked() {
        appNavigator.tryNavigateTo(Destination.MessagesScreen())
    }

    fun onNavigateToDetailsButtonClicked() {
        appNavigator.tryNavigateTo(Destination.DetailsScreen())
    }
}
