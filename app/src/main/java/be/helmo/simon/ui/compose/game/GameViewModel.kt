package be.helmo.simon.ui.compose.game

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.domain.GameRules
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration
import kotlin.random.Random

class GameViewModel(

) : ViewModel() {

    private val MILLIS_VALUE: Long = 1000

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var gameRules: GameRules = GameRules()

    private fun toggleClick() {
        val value = _uiState.value.canClick
        _uiState.update {
            it.copy(canClick = !value)
        }
    }

    private fun glimpseLight(color: GameTileUiState) {
        viewModelScope.launch {
            color.toggleColor()

            delay(Duration.ofMillis(MILLIS_VALUE))

            color.toggleColor()
        }
    }

    fun onButtonClicked(color: GameTileUiState) {
        color.toggleColor()
    }

    private fun showSequence() {
        toggleClick()

        val sequence = gameRules.sequence

        for (note in sequence) {
            val gameTileNote = findGameTile(note) ?: continue
            Handler(Looper.getMainLooper()).postDelayed({
                glimpseLight(gameTileNote)
            }, MILLIS_VALUE)
        }

        toggleClick()
    }

    private fun findGameTile(charValue: Char): GameTileUiState? {
        val colors = _uiState.value.colors
        return colors.find {
            it.value == charValue
        }
    }

    private fun showError() {
        //TODO
    }

    fun saveScore() {
        //TODO
    }
}