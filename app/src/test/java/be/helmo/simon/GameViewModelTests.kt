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



}