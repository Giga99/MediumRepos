package com.medium.viewpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medium.viewpager.ui.theme.MediumReposTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediumReposTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.MAIN,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(Route.MAIN) {
                            MainScreen(navController)
                        }

                        composable(Route.HORIZONTAL) {
                            HorizontalPagerScreen()
                        }

                        composable(Route.VERTICAL) {
                            VerticalPagerScreen()
                        }
                    }
                }
            }
        }
    }
}

object Route {
    const val MAIN = "main"
    const val HORIZONTAL = "horizontal"
    const val VERTICAL = "vertical"
}
