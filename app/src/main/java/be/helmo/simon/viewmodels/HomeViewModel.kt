package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val scoreRepository: ScoreRepository = ScoreRepository.get()
) : ViewModel() {

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
        }
    }
}