package be.helmo.simon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.helmo.simon.ui.game.compose.GameScreen
import be.helmo.simon.ui.home.HomeScreen
import be.helmo.simon.ui.savescore.SaveScoreScreen
import be.helmo.simon.ui.scoreboard.ScoreBoardScreen

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
                    navController.navigate(Screen.ScoreBoard.route)
                }
            )
        }

        composable(
            route = Screen.Game.route
        ) {
            GameScreen(
                onBackArrowClicked = {
                    navController.navigateUp() //TODO trouver un moyen de popstack avec les arguments pour retirer l'id directement
                }
            )
        }

        composable(
            route = Screen.SaveScore.route
        ) {
            SaveScoreScreen()
        }

        composable(
            route = Screen.ScoreBoard.route
        ) {
            ScoreBoardScreen()
        }
    }
}