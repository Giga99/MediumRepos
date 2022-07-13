package com.medium.permissions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medium.permissions.ui.theme.MediumReposTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediumReposTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.MAIN
                    ) {
                        composable(Route.MAIN) {
                            MainScreen(navController)
                        }

                        composable(Route.DEFAULT) {
                            BuiltInPermissionsScreen()
                        }

                        composable(Route.ACCOMPANIST) {
                            AccompanistPermissionsScreen()
                        }
                    }
                }
            }
        }
    }
}

object Route {
    const val MAIN = "main"
    const val DEFAULT = "default"
    const val ACCOMPANIST = "accompanist"
}
