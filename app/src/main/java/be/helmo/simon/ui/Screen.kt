package be.helmo.simon.ui

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object Game : Screen("game")

    data object SaveScore : Screen("save_score")

    data object ScoreBoard : Screen("scoreboard")
}