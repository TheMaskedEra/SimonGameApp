package be.helmo.simon.ui.game

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

class GameViewModel(

) : ViewModel() {

    private val millisValue: Long = 500

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var gameRules: GameRules = GameRules()

    init {
        showSequence()
    }

    private fun toggleClick() {
        _uiState.update {
            it.copy(canClick = !it.canClick)
        }
    }

    private suspend fun glimpseLight(color: GameTileUiState) {
        color.toggleColor()

        delay(Duration.ofMillis(millisValue))

        color.toggleColor()
    }

    fun onButtonClicked(currentColor: GameTileUiState) {
        viewModelScope.launch {
            toggleClick()

            glimpseLight(currentColor)
            val result = gameRules.checkNote(currentColor.value)

            when(result) {
                -1 -> showError()
                1 -> {
                    _uiState.update {
                        it.copy(score = it.score + 1)
                    }
                    delay(Duration.ofMillis(100))
                    showSequence()
                }
            }

            toggleClick()
        }
    }

    private fun showSequence() {
        val sequence = gameRules.sequence

        viewModelScope.launch {
            toggleClick()

            for (note in sequence) {
                val gameTile = findGameTileState(note) ?: continue
                glimpseLight(gameTile)
                delay(Duration.ofMillis(100))
            }
            toggleClick()
        }
    }

    private fun findGameTileState(charValue: Char): GameTileUiState? {
        return when (charValue) {
            GameTileUiState.Red.value -> GameTileUiState.Red
            GameTileUiState.Yellow.value -> GameTileUiState.Yellow
            GameTileUiState.Blue.value -> GameTileUiState.Blue
            GameTileUiState.Green.value -> GameTileUiState.Green
            else -> null
        }
    }

    private fun showError() {
        //TODO
    }

    fun saveScore() {
        //TODO
    }
}