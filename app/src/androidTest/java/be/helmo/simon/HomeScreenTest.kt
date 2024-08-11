package be.helmo.simon

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import be.helmo.simon.ui.HomeScreen
import be.helmo.simon.viewmodels.FakeHomeViewModel
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

class HomeScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testPlayButtonExistsAndClick() {
        var playButtonClicked = false

        var playButtonText = ""

        composeTestRule.setContent {
            val context = LocalContext.current
            playButtonText = context.getString(R.string.play_title)

            HomeScreen(
                onPlayClicked = { playButtonClicked = true },
                onTrophyClicked = {  }
            )
        }

        val playButton = composeTestRule.onNodeWithText(playButtonText)
        playButton.assertIsDisplayed()

        playButton.performClick()
        assertTrue("The Play button should trigger onPlayClicked", playButtonClicked)
    }

    @Test
    fun testTrophyScoresExistsAndClick() {
        var trophyButtonClicked = false
        var contentDescription = ""

        composeTestRule.setContent {
            val context = LocalContext.current
            contentDescription = context.getString(R.string.score_button_description)

            HomeScreen(
                onPlayClicked = {},
                onTrophyClicked = { trophyButtonClicked = true }
            )
        }

        val trophyButton = composeTestRule.onNodeWithContentDescription(contentDescription)
        trophyButton.assertIsDisplayed()
        trophyButton.performClick()
        assertTrue("The trophy button should trigger onTrophyClicked", trophyButtonClicked)
    }

    @Test
    fun testScoresIsDisplayedWhenExists() {
        val fakeScores = listOf(
            ScoreItemUiState(score = 3, name = "Alexis", date = LocalDateTime.now()),
            ScoreItemUiState(score = 2, name = "Gaelle", date = LocalDateTime.now()),
            ScoreItemUiState(score = 1, name = "Alexis", date = LocalDateTime.now())
        )

        var scores = emptyList<String>()

        val fakeViewModel = FakeHomeViewModel(fakeScores)
        composeTestRule.setContent {
            val context = LocalContext.current
            HomeScreen(
                onPlayClicked = {  },
                onTrophyClicked = {  },
                homeViewModel = fakeViewModel
            )

            scores = fakeScores.mapIndexed { index, score ->
                context.getString(R.string.score_text, index + 1, score.score)
            }
        }

        for (expectedText in scores) {
            composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
        }
    }
}