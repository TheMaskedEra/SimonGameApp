package be.helmo.simon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.helmo.simon.ui.game.compose.GameScreen
import be.helmo.simon.ui.home.HomeScreen

@Composable
fun SimonApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onPlayClicked = {
                    navController.navigate(Screen.Game.route)
                },
                onTrophyClicked = {
                    /*TODO*/
                }
            )
        }

        composable(
            route = Screen.Game.route
        ) {
            GameScreen(
                onBackArrowClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}