package be.helmo.simon.viewmodels.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import be.helmo.simon.viewmodels.GameTileEnum
import kotlinx.coroutines.delay

sealed class GameTileUiState(
    private val gameTileType: GameTileEnum,
) {

    val soundResource = gameTileType.soundResource

    val value: Char = gameTileType.value
    var currentColor by mutableStateOf(gameTileType.idleColor)
        private set


    var toggle by mutableStateOf(false)
        private set

    private fun toggleColor() {
        currentColor = if (currentColor == gameTileType.activeColor) {
            gameTileType.idleColor
        } else {
            gameTileType.activeColor
        }
        toggle = !toggle
    }

    suspend fun glimpseColor(time: Long) {
        toggleColor()

        delay(time)

        toggleColor()
    }

    data object Red : GameTileUiState(GameTileEnum.Red)

    data object Yellow : GameTileUiState(GameTileEnum.Yellow)

    data object Blue : GameTileUiState(GameTileEnum.Blue)

    data object Green : GameTileUiState(GameTileEnum.Green)

    companion object {

        fun findGameTileUiState(charValue: Char): GameTileUiState? {
            return when (charValue) {
                Red.value -> Red
                Yellow.value -> Yellow
                Blue.value -> Blue
                Green.value -> Green
                else -> null
            }
        }
    }
}