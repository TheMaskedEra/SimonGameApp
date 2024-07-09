package be.helmo.simon.viewmodels

import androidx.compose.ui.graphics.Color
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
    val soundResource: String //TODO
) {
    Red('R', idleRed, activeRed, ""),

    Yellow('Y', idleYellow, activeYellow, ""),

    Blue('B', idleBlue, activeBlue, ""),

    Green('G', idleGreen, activeGreen, "")
}