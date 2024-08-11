package be.helmo.simon.viewmodels.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import be.helmo.simon.viewmodels.GameTileEnum
import kotlinx.coroutines.delay

sealed class GameTileUiState(
    gameTileType: GameTileEnum,
) {

    val soundResource = gameTileType.soundResource

    val value: Char = gameTileType.value


    var toggle by mutableStateOf(false)
        private set

    suspend fun glimpseColor(time: Long) {
        toggle = !toggle

        delay(time)

        toggle = !toggle
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