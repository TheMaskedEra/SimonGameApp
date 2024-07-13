package be.helmo.simon

import be.helmo.simon.data.Score
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.viewmodels.HomeViewModel
import be.helmo.simon.viewmodels.states.ScoreItemUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTests {

    private lateinit var mockRepo: ScoreRepository
    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        mockRepo = mock(ScoreRepository::class.java)
    }

    @Test
    fun `best scores is empty and shows nothing`() = runTest {
        `when`(mockRepo.getBestScores()).thenReturn(emptyList())
        val expectedList: List<ScoreItemUiState> = emptyList()

        val viewModel = HomeViewModel(mockRepo)

        val scores = viewModel.bestScores

        assert(scores.value == expectedList)
    }

    @Test
    fun `number of best scores is under 3 and shows everything`() = runTest {
        val scoreList = listOf(
            Score(name = "Alexis", score = 1),
            Score(name = "Alexis", score = 0)
        )
        val scoreItemUiStateList = scoreList.map { score ->
            ScoreItemUiState(
                name = score.name,
                score = score.score,
                date = score.date
            )
        }

        `when`(mockRepo.getBestScores()).thenReturn(scoreList)

        val viewModel = HomeViewModel(mockRepo)
        val scores = viewModel.bestScores.value

        assertEquals(scoreItemUiStateList, scores)
    }

    @Test
    fun `Number of scores is greater than 3 but shows the only 3 bests`() = runTest {
        val scoreList = listOf(
            Score(name = "Gaelle", score = 84),
            Score(name = "Alexis", score = 12),
            Score(name = "Alexis", score = 11),
            Score(name = "Gaelle", score = 1)
        )
        val scoreItemUiStateList = scoreList.map { score ->
            ScoreItemUiState(
                name = score.name,
                score = score.score,
                date = score.date
            )
        }.dropLast(1)

        `when`(mockRepo.getBestScores()).thenReturn(scoreList)

        val viewModel = HomeViewModel(mockRepo)
        val scores = viewModel.bestScores.value

        assertEquals(scoreItemUiStateList, scores)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}