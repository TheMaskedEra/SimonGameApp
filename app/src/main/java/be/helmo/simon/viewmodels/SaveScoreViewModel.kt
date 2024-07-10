package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import be.helmo.simon.data.ScoreRepository

class SaveScoreViewModel(

): ViewModel() {

    private val scoreRepository: ScoreRepository = ScoreRepository.get()

    fun saveScore(score: Int) {

    }

}