package com.medium.navigation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medium.navigation.R

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
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
                onClick = { detailsViewModel.onBackButtonClicked() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "")
            }
        }
        Text(
            text = "DetailsScreen",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { detailsViewModel.onNavigateToCurrentUserDetailsButtonClicked() }
        ) {
            Text(text = "Navigate to Current User Details")
        }
    }
}
