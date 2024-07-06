package be.helmo.simon.ui.compose.game

import androidx.compose.ui.graphics.Color
import be.helmo.simon.ui.theme.activeBlue
import be.helmo.simon.ui.theme.activeGreen
import be.helmo.simon.ui.theme.activeRed
import be.helmo.simon.ui.theme.activeYellow
import be.helmo.simon.ui.theme.idleBlue
import be.helmo.simon.ui.theme.idleGreen
import be.helmo.simon.ui.theme.idleRed
import be.helmo.simon.ui.theme.idleYellow

abstract class GameTileUiState(
    private val gameTileType: GameTileEnum,
    var currentColor: Color = gameTileType.idleColor
) {
    data object Red : GameTileUiState(GameTileEnum.Red)

    data object Yellow : GameTileUiState(GameTileEnum.Yellow)

    data object Blue : GameTileUiState(GameTileEnum.Blue)

    data object Green : GameTileUiState(GameTileEnum.Green)

    fun toggleColor() {
        if (currentColor == gameTileType.idleColor) {
            currentColor = gameTileType.activeColor
        } else {
            currentColor = gameTileType.idleColor
        }
    }
}

enum class GameTileEnum(
    val idleColor: Color,
    val activeColor: Color,
    val soundResource: String //TODO
) {
    Red(idleRed, activeRed, ""),

    Yellow(idleYellow, activeYellow, ""),

    Blue(idleBlue, activeBlue, ""),

    Green(idleGreen, activeGreen, "")
}