package be.helmo.simon.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.data.SimonDatabase
import kotlinx.coroutines.launch

class ScoreBoardViewModel(
) : ViewModel() {

    private val scoreRepository: ScoreRepository = ScoreRepository.get()

    var scores: List<Score> = emptyList()

    init {
        viewModelScope.launch {
            scores = scoreRepository.getAll()
        }
    }

    fun getShareIntent(): Intent { //TODO trouver quel argument donner pour retrouver le score dans la db
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "the text to send")
            type = "text/plain"
        }

        return Intent.createChooser(sendIntent, null)
    }
}