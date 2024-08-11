package be.helmo.simon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SimonNavGraph() {
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
                    navController.navigateUp()
                },
                onGameFinished = { score ->
                    navController.navigate(Screen.SaveScore.withArgs(score.toString())) {
                        popUpTo(Screen.Game.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.SaveScore.route + "/{score}",
            arguments = listOf(
                navArgument("score") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            entry.arguments?.getInt("score")?.let {
                SaveScoreScreen(
                    score = it,
                    onCloseCalled = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onSaveAction = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(
            route = Screen.ScoreBoard.route
        ) {
            ScoreBoardScreen(
                backAction = {
                    navController.navigateUp()
                }
            )
        }
    }
}