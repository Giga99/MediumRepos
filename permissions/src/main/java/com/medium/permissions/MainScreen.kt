package com.medium.permissions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.medium.permissions.Route

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Route.DEFAULT) }) {
            Text(text = "Check built-in permission requester")
        }
        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = { navController.navigate(Route.ACCOMPANIST) }
        ) {
            Text(text = "Check accompanist permissions library")
        }
    }
}
