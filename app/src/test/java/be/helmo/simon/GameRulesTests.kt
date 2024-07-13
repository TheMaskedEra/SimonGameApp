package be.helmo.simon

import be.helmo.simon.domain.GameRules
import org.junit.Test

class GameRulesTests {

    @Test
    fun shouldHaveAMoveFromStart() {
        val rules = GameRules()
        val sequence = rules.sequence

        assert(sequence.isNotEmpty())
    }

    @Test
    fun shouldReturnOneWhenCheckingValidMoveWithLastValidMove() {
        val rules = GameRules()
        val sequence = rules.sequence
        val firstMove = sequence[0]

        assert(rules.checkNote(firstMove) == 1)
    }

    @Test
    fun shouldReturnZeroWhenCheckingValidMoveWithValidMoveAndMoreMoves() {
        val rules = GameRules()
        val sequence = rules.sequence
        val firstMove = sequence[0]
        rules.checkNote(firstMove)

        assert(rules.checkNote(firstMove) == 0)
    }

    @Test
    fun shouldReturnNegativeOneWhenCheckingValidMoveWithBadResult() {
        val rules = GameRules()
        val firstMove = '2'

        assert(rules.checkNote(firstMove) == -1)
    }
}