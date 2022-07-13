package com.medium.viewpager

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
        Button(onClick = { navController.navigate(Route.HORIZONTAL) }) {
            Text(text = "Check horizontal pager")
        }
        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = { navController.navigate(Route.VERTICAL) }
        ) {
            Text(text = "Check vertical pager")
        }
    }
}
