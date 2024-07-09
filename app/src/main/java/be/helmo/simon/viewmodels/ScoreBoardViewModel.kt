package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreDatabase
import kotlinx.coroutines.launch

class ScoreBoardViewModel(
    private val scoreRepository: ScoreDatabase //TODO
) : ViewModel() {

    var scores: List<Score> = emptyList()

    init {
        viewModelScope.launch {
            scores = scoreRepository.scoreDao().getAll()
        }
    }

    fun onShareButtonClicked() { //TODO trouver quel argument donner pour retrouver le score dans la db

    }

}