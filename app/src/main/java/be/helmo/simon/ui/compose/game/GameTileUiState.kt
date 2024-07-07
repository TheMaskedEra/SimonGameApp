package be.helmo.simon.ui.compose.game

import android.util.Log
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
    var value: Char = gameTileType.value,
    var currentColor: Color = gameTileType.idleColor
) {
    data object Red : GameTileUiState(GameTileEnum.Red)

    data object Yellow : GameTileUiState(GameTileEnum.Yellow)

    data object Blue : GameTileUiState(GameTileEnum.Blue)

    data object Green : GameTileUiState(GameTileEnum.Green)

    fun toggleColor() {
        currentColor = if (currentColor == gameTileType.idleColor) {
            Log.d("Glimpse light", "Active Color")
            gameTileType.activeColor
        } else {
            Log.d("Glimpse light", "Idle color")
            gameTileType.idleColor
        }
    }
}

enum class GameTileEnum(
    val value: Char,
    val idleColor: Color,
    val activeColor: Color,
    val soundResource: String //TODO
) {
    Red('R', idleRed, activeRed, ""),

    Yellow('Y', idleYellow, activeYellow, ""),

    Blue('B', idleBlue, activeBlue, ""),

    Green('G', idleGreen, activeGreen, "")
}