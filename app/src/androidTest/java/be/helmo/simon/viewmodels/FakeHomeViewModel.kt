package be.helmo.simon.viewmodels

import be.helmo.simon.viewmodels.states.ScoreItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeHomeViewModel(
    private val scores: List<ScoreItemUiState>
) : HomeViewModel() {
    override val bestScores = MutableStateFlow(scores).asStateFlow()
}