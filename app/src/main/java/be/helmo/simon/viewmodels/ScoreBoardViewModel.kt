package be.helmo.simon.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.data.SimonDatabase
import be.helmo.simon.ui.Screen
import be.helmo.simon.viewmodels.states.ScoreBoardUiState
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScoreBoardViewModel(
) : ViewModel() {

    private val scoreRepository: ScoreRepository = ScoreRepository.get()

    private val _scoreBoardUiState: MutableStateFlow<ScoreBoardUiState> = MutableStateFlow(ScoreBoardUiState())
    val scoreBoardUiState: StateFlow<ScoreBoardUiState> = _scoreBoardUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val scores = scoreRepository.getAll()
            val scoresUiState: MutableList<ScoreItemUiState> = mutableListOf()

            for (score in scores) {
                val scoreUiState = ScoreItemUiState(
                    score = score.score,
                    name = score.name,
                    date = score.date)

                scoresUiState.add(scoreUiState)
            }

            _scoreBoardUiState.update {
                it.copy(scores = scoresUiState)
            }
        }
    }

    fun getShareIntent(score: Int): Intent { //TODO trouver quel argument donner pour retrouver le score dans la db
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Je viens de faire $score points sur Simon ! Seras-tu me battre ?")
            type = "text/plain"
        }

        return Intent.createChooser(sendIntent, null)
    }
}