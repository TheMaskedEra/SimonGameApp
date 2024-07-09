package be.helmo.simon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import be.helmo.simon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveScoreScreen(
    score: Int,
    onCloseCalled: () -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Sauvegarder mon score")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onCloseCalled
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "Annuler la sauvegarde")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_check), contentDescription = "Confirmer la sauvegarde")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Le score est de $score points")

            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = {
                    Text(text = "Nom")
                }
            )
        }
    }
}

@Preview
@Composable
fun SaveScoreScreenPreview() {
    SaveScoreScreen(score = 3, ) {

    }
}