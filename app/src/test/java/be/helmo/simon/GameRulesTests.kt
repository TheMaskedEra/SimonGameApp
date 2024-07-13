package be.helmo.simon

import be.helmo.simon.domain.GameRules
import org.junit.Test

class GameRulesTests {

    @Test
    fun `should have a move from start`() {
        val rules = GameRules()
        val sequence = rules.sequence

        assert(sequence.isNotEmpty())
    }

    @Test
    fun `should return one when checking valid move with last valid move`() {
        val rules = GameRules()
        val sequence = rules.sequence
        val firstMove = sequence[0]

        assert(rules.checkNote(firstMove) == 1)
    }

    @Test
    fun `should return zero when checking valid move with valid move and more moves`() {
        val rules = GameRules()
        val sequence = rules.sequence
        val firstMove = sequence[0]
        rules.checkNote(firstMove)

        assert(rules.checkNote(firstMove) == 0)
    }

    @Test
    fun `should return negative one when checking move with bad result`() {
        val rules = GameRules()
        val firstMove = '2'

        assert(rules.checkNote(firstMove) == -1)
    }
}