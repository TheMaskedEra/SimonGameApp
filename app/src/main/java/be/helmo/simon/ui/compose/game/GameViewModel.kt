package be.helmo.simon.ui.compose.game

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.time.delay
import java.time.Duration
import kotlin.random.Random

class GameViewModel(

) : ViewModel() {

    private val MILLIS_VALUE: Long = 1000

    private val _uiState: MutableStateFlow<GameUiState> = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val _sequence: MutableList<GameTileUiState> = mutableListOf()
    private var _currentIndexMove: Int = 0

    init {
        newGame()
    }

    private fun newGame() {
        _currentIndexMove = 0
        nextMove()
        showSequence()
    }

    private fun toggleClick() {
        val value = _uiState.value.canClick
        _uiState.update {
            it.copy(canClick = !value)
        }
    }

    private suspend fun glimpseLight(color: GameTileUiState) {
        color.toggleColor()

        delay(Duration.ofMillis(MILLIS_VALUE))

        color.toggleColor()
    }

    fun onButtonClicked(color: GameTileUiState) {

        toggleClick()
        val size = _sequence.size

        glimpseLight(color)

        val current = _sequence[_currentIndexMove]
        if (current != color) {
            showError()
        }

        if (_currentIndexMove == size && _sequence[_currentIndexMove] == color) {
            nextMove()
            _currentIndexMove = 0
        } else {
            _currentIndexMove++
        }

        toggleClick()
        showSequence()
    }

    private fun nextMove() {
        val randomValue = Random.nextInt(0, 4)
        val next = _uiState.value.colors.elementAt(randomValue)

        _sequence.add(next)
    }

    private fun showSequence() {
        toggleClick()

        for (tile in _sequence) {
            glimpseLight(tile)

            delay(Duration.ofMillis(MILLIS_VALUE))
        }

        toggleClick()
    }

    private fun showError() {
        //TODO
    }

    fun saveScore() {

    }
}