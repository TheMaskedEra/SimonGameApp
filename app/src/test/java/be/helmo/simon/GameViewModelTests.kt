package be.helmo.simon

import be.helmo.simon.domain.GameRules
import be.helmo.simon.viewmodels.GameViewModel
import be.helmo.simon.viewmodels.states.GameTileUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTests {

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var mockGameRules: GameRules

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        mockGameRules = mock()
    }

    @Test
    fun `onButtonClicked makes the button glimpes`() = runTest {
        val testColor: GameTileUiState = GameTileUiState.Green
        val viewModel = GameViewModel(mockGameRules)

        `when`(mockGameRules.checkNote(testColor.value)).thenReturn(1)

        verify(testColor.glimpseColor(500))
    }

    @Test
    fun `onButtonClicked toggles click state and checks note`() = runTest {
        val testColor = GameTileUiState.Green
        val viewModel = GameViewModel(mockGameRules)

        `when`(mockGameRules.checkNote(testColor.value)).thenReturn(1)

        viewModel.onButtonClicked(testColor)

        verify(mockGameRules).checkNote(testColor.value)
        assertEquals(1, viewModel.uiState.value.score)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}