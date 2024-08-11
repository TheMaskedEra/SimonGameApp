package be.helmo.simon.viewmodels

import be.helmo.simon.R

enum class GameTileEnum(
    val value: Char,
    val soundResource: Int
) {
    Red('R', R.raw.note1),

    Yellow('Y',  R.raw.note2),

    Blue('B',  R.raw.note3),

    Green('G',  R.raw.note4)
}