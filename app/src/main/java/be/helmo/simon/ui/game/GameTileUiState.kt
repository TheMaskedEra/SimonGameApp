package be.helmo.simon.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class GameTileUiState(
    private val gameTileType: GameTileEnum,
) {

    val value: Char = gameTileType.value
    var currentColor by mutableStateOf(gameTileType.idleColor)
        private set

    fun toggleColor() {
        currentColor = if (currentColor == gameTileType.activeColor) {
            gameTileType.idleColor
        } else {
            gameTileType.activeColor
        }
    }

    data object Red : GameTileUiState(GameTileEnum.Red)

    data object Yellow : GameTileUiState(GameTileEnum.Yellow)

    data object Blue : GameTileUiState(GameTileEnum.Blue)

    data object Green : GameTileUiState(GameTileEnum.Green)
}