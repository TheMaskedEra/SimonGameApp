package be.helmo.simon.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.lifecycle.viewmodel.compose.viewModel
import be.helmo.simon.R
import be.helmo.simon.viewmodels.ScoreBoardViewModel
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBoardScreen(
    backAction: () -> Unit,
    scoreBoardViewModel: ScoreBoardViewModel = viewModel()
) {
    val uiState = scoreBoardViewModel.scoreBoardUiState.collectAsState()
    val scores = uiState.value.scores

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Scores")
                },
                navigationIcon = {
                    IconButton(
                        onClick = backAction
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retourner au menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(scores.size) { index ->
                val item = scores[index]
                ScoreItem(
                    getShareIntent = {
                        scoreBoardViewModel.getShareIntent(item.score)
                    },
                    item
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ScoreItem(
    getShareIntent: (Int) -> Intent,
    scoreItem: ScoreItemUiState
) {
    val context = LocalContext.current
    val score = scoreItem.score
    val date = scoreItem.date

    val dateFormat: String = String.format("Le %d/%02d %d à %d:%02d", date.dayOfMonth, date.monthValue, date.year, date.hour, date.minute)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    modifier = Modifier,
                    text = scoreItem.name,
                    fontSize = TextUnit(25f, TextUnitType.Sp)
                )

                Text(dateFormat)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    val shareIntent = getShareIntent(score)
                    context.startActivity(shareIntent)
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_share
                    ),
                    contentDescription = "Partager le score"
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text =score.toString(),
            fontSize = TextUnit(15f, TextUnitType.Em)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreItemPreview() {
    val getShareIntent: (Int) -> Intent = {
        Intent()
    }
    val scoreItem = ScoreItemUiState(
        score = 4,
        name = "Alexis",
        date = LocalDateTime.now()
    )

    ScoreItem(getShareIntent = getShareIntent, scoreItem = scoreItem)
}