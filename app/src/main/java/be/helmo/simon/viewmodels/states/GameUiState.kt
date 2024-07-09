package be.helmo.simon.viewmodels.states

data class GameUiState(
    var score: Int = 0,
    var canClick: Boolean = true,

    var isEnd: Boolean = false
)
