package be.helmo.simon.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.helmo.simon.R
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.viewmodels.HomeViewModel
import be.helmo.simon.viewmodels.states.ScoreItemUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPlayClicked: () -> Unit,
    onTrophyClicked: () -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val scores = homeViewModel.bestScores.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = onTrophyClicked) {
                        Icon(painter = painterResource(id = R.drawable.ic_trophy), contentDescription = "Ranking Button")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onPlayClicked) {
                    Text(text = stringResource(R.string.play_title))
                }

                if (!scores.value.isEmpty()) {
                    Spacer(modifier = Modifier.height(50.dp))
                    ScoreList(scores)
                }
            }
        }
    }
}

@Composable
fun ScoreList(
    scores: State<List<ScoreItemUiState>>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scoresValue = scores.value.take(3)

        for ((index, score) in scoresValue.withIndex()) {
            val size = 25 - (index * 5)

            Text(
                text = String.format("#%d %d points", index + 1, score.score),
                fontSize = size.sp
            )
        }
    }
}