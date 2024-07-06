package be.helmo.simon.ui.compose.game

data class GameUiState(
    var score: Int = 0,
    var canClick: Boolean = true,

    val colors: Set<GameTileUiState> = setOf(
        GameTileUiState.Blue,
        GameTileUiState.Green,
        GameTileUiState.Red,
        GameTileUiState.Yellow,
    )
)
