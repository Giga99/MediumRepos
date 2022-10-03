package com.medium.navigation.user_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medium.navigation.R

@Composable
fun UserDetailsScreen(
    userDetailsViewModel: UserDetailsViewModel = hiltViewModel()
) {
    val viewState = userDetailsViewModel.viewState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { userDetailsViewModel.onBackButtonClicked() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "")
            }
        }
        Text(
            text = "UserDetailsScreen",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "${viewState.firstName} ${viewState.lastName}"
        )
    }
}
