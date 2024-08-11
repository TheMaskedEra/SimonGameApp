package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreRepository
import kotlinx.coroutines.launch

class SaveScoreViewModel(

): ViewModel() {

    private val scoreRepository: ScoreRepository = ScoreRepository.get()

    fun saveScore(
        score: Int,
        name: String
    ) {
        val newScore = Score(name = name, score = score)

        viewModelScope.launch {
            scoreRepository.insertScore(newScore)
        }
    }

}