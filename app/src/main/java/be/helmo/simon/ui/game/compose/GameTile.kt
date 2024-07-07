package be.helmo.simon.ui.game.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import be.helmo.simon.ui.game.GameTileUiState
import be.helmo.simon.ui.game.GameUiState

@Composable
fun GameTile(
    color: GameTileUiState,
    state: GameUiState,
    onClick: (GameTileUiState) -> Unit
) {
    val shape = RectangleShape

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape)
                .background(color.currentColor)
                .clickable(
                    enabled = state.canClick,
                    onClick = {
                        onClick.invoke(color)
                    }
                )
        )
    }
}