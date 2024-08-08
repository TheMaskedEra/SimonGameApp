package be.helmo.simon.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import be.helmo.simon.viewmodels.states.GameTileUiState
import be.helmo.simon.viewmodels.states.GameUiState

@Composable
fun GameTile(
    gameTileState: GameTileUiState,
    gameState: GameUiState,
    onClick: () -> Unit
) {
    val shape = RectangleShape

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(shape)
            .background(gameTileState.currentColor)
            .clickable(
                enabled = gameState.canClick,
                onClick = {
                    onClick()
                }
            )
    )
}