package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.domain.GameRules
import be.helmo.simon.viewmodels.states.GameTileUiState
import be.helmo.simon.viewmodels.states.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class GameViewModel(
    private val gameRules: GameRules = GameRules()
) : ViewModel() {

    private val millisValue: Long = 500

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        showSequence()
    }

    fun onButtonClicked(currentColor: GameTileUiState) {
        viewModelScope.launch {
            toggleClick()

            currentColor.glimpseColor(millisValue)
            val result = gameRules.checkNote(currentColor.value)

            when(result) {
                -1 -> showEnding()
                1 -> {
                    incrementScore()
                    showSequence()
                }
            }

            toggleClick()
        }
    }

    private fun toggleClick() {
        _uiState.update {
            it.copy(canClick = !it.canClick)
        }
    }

    private fun showSequence() {
        val sequence = gameRules.sequence

        viewModelScope.launch {
            toggleClick()
            delay(100)
            for (note in sequence) {
                val gameTile = GameTileUiState.findGameTileUiState(note) ?: continue
                gameTile.glimpseColor(millisValue)
                delay(50)
            }

            toggleClick()
        }
    }

    private fun showEnding() {
        _uiState.update {
            it.copy(isEnd = true)
        }
    }

    private fun incrementScore() {
        _uiState.update {
            it.copy(score = it.score + 1)
        }
    }
}