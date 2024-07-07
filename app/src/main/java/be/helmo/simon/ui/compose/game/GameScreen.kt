package be.helmo.simon.ui.compose.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.helmo.simon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    onBackArrowClicked: () -> Unit,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(stringResource(R.string.app_name))
                },

                navigationIcon = {
                    IconButton(onClick = onBackArrowClicked) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Return to menu")
                    }
                },
            )
        },
    ) { contentPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            val score = gameUiState.score

            Text(
                text = "Current Score : $score",
                Modifier.align(Alignment.CenterHorizontally)
            )

            Box(
                contentAlignment = Alignment.Center,
            ) {

                Row(
                    modifier = Modifier.requiredHeight(250.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        GameTile(
                            GameTileUiState.Blue,
                            gameUiState
                        ) {
                            gameViewModel.onButtonClicked(GameTileUiState.Blue)
                        }

                        GameTile(
                            GameTileUiState.Green,
                            gameUiState
                        ) {
                            gameViewModel.onButtonClicked(GameTileUiState.Green)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        GameTile(
                            GameTileUiState.Red,
                            gameUiState
                        ) {
                            gameViewModel.onButtonClicked(GameTileUiState.Red)
                        }

                        GameTile(
                            GameTileUiState.Yellow,
                            gameUiState
                        ) {
                            gameViewModel.onButtonClicked(GameTileUiState.Yellow)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen({})
}