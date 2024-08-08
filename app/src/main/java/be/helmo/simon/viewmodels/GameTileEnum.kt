package be.helmo.simon.viewmodels

import androidx.compose.ui.graphics.Color
import be.helmo.simon.R
import be.helmo.simon.ui.theme.activeBlue
import be.helmo.simon.ui.theme.activeGreen
import be.helmo.simon.ui.theme.activeRed
import be.helmo.simon.ui.theme.activeYellow
import be.helmo.simon.ui.theme.idleBlue
import be.helmo.simon.ui.theme.idleGreen
import be.helmo.simon.ui.theme.idleRed
import be.helmo.simon.ui.theme.idleYellow

enum class GameTileEnum(
    val value: Char,
    val idleColor: Color,
    val activeColor: Color,
    val soundResource: Int
) {
    Red('R', idleRed, activeRed, R.raw.note1),

    Yellow('Y', idleYellow, activeYellow, R.raw.note2),

    Blue('B', idleBlue, activeBlue, R.raw.note3),

    Green('G', idleGreen, activeGreen, R.raw.note4)
}