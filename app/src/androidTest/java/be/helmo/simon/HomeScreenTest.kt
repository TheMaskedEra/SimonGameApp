package be.helmo.simon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import be.helmo.simon.ui.HomeScreen
import be.helmo.simon.ui.theme.SimonTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testPlayButtonClicked() {
        var playButtonClicked = false

        composeTestRule.setContent {
            HomeScreen(
                onPlayClicked = { playButtonClicked = true },
                onTrophyClicked = {  }
            )
        }

        composeTestRule.onNodeWithText("Jouer").performClick()
        assert(playButtonClicked)
    }
}