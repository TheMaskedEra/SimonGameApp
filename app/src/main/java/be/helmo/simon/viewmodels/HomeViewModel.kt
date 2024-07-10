package be.helmo.simon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.SimonDatabase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val scoreRepository: SimonDatabase
) : ViewModel() {

    var bestScores: Array<Score> = emptyArray()

    init {
        viewModelScope.launch {
            bestScores = scoreRepository.scoreDao().getBestScores()
        }
    }
}