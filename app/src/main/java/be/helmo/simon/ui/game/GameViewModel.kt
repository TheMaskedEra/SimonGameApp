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
            gameRules.checkNote(currentColor.value)

            toggleClick()
        }
    }

    private fun showError() {
        //TODO
    }

    fun saveScore() {
        //TODO
    }
}