package be.helmo.simon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import be.helmo.simon.R
import be.helmo.simon.viewmodels.ScoreBoardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBoardScreen(
    backAction: () -> Unit,
    scoreBoardViewModel: ScoreBoardViewModel = viewModel()
) {
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
            items(5) {
                ScoreItem(
                    onShareButtonClicked = { scoreBoardViewModel.onShareButtonClicked() }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ScoreItem(
    onShareButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(

        ) {
            Text("Score")
            Text("Name")
        }

        IconButton(
            onClick = {

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
}

@Preview
@Composable
fun ScoreBoardScreenPreview() {
    ScoreBoardScreen(
        backAction = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ScoreItemPreview() {
    ScoreItem {}
}