package be.helmo.simon.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.data.SimonDatabase
import be.helmo.simon.viewmodels.states.GameUiState
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
) : ViewModel() {

    private val scoreRepository: ScoreRepository = ScoreRepository.get()

    private var _bestScores: MutableStateFlow<List<ScoreItemUiState>> = MutableStateFlow(emptyList())
    val bestScores: StateFlow<List<ScoreItemUiState>> = _bestScores.asStateFlow()

    init {
        viewModelScope.launch {
            val scores = scoreRepository.getBestScores()
            if (scores.isEmpty()) {
                return@launch
            }

            val numberOfScores = if (scores.size < 3) scores.size else 3

            val bestOf3 = scores.subList(0, numberOfScores)

            val scoreStateList = bestOf3.map { score ->
                ScoreItemUiState(
                    score = score.score,
                    name = score.name,
                    date = score.date
                )
            }

            _bestScores.value = scoreStateList
            Log.d("Best Scores", bestScores.toString())
        }
    }
}