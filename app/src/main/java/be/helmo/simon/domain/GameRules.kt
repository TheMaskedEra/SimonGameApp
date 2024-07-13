package be.helmo.simon.domain

import kotlin.random.Random

class GameRules {

    private val colorValues = arrayOf('G', 'Y', 'B', 'R')

    private val _sequence: MutableList<Char> = mutableListOf()
    val sequence: List<Char> = _sequence

    private var _currentIndexNote: Int = 0

    init {
        generateNextNote()
    }

    private fun generateNextNote() {
        _currentIndexNote = 0

        val randomValue = Random.nextInt(0, colorValues.size)
        val randomChar = colorValues[randomValue]
        _sequence.add(randomChar)
    }

    fun checkNote(note: Char): Int {
        val validMove = sequence[_currentIndexNote]

        if (validMove != note) {
            return -1
        } else if (_currentIndexNote < _sequence.size -1) {
            _currentIndexNote++
            return 0
        }

        generateNextNote()
        return 1
    }
}