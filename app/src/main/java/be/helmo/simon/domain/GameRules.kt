package be.helmo.simon.domain

import kotlin.random.Random

class GameRules {

    private val CHAR_VALUE = arrayOf('G', 'Y', 'B', 'R')

    private val _sequence: MutableList<Char> = mutableListOf()
    val sequence: List<Char> = _sequence

    private var _currentIndexNote: Int = 0

    init {
        generateNextNote()
    }

    fun generateNextNote() {
        _currentIndexNote = 0

        val randomValue = Random.nextInt(0, CHAR_VALUE.size)
        val randomChar = CHAR_VALUE[randomValue]
        _sequence.add(randomChar)
    }

    fun checkNote(note: Char) {
        val validMove = sequence[_currentIndexNote]

        if (validMove != note) {
            //TODO Cas d'échec
        } else {
            //TODO cas de réussite, trouver si c'est la dernière séquence à trouver?
        }
    }
}