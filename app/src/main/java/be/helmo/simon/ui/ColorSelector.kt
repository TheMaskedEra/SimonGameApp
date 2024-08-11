package be.helmo.simon.ui

import androidx.compose.ui.graphics.Color
import be.helmo.simon.ui.theme.activeBlue
import be.helmo.simon.ui.theme.activeGreen
import be.helmo.simon.ui.theme.activeRed
import be.helmo.simon.ui.theme.activeYellow
import be.helmo.simon.ui.theme.idleBlue
import be.helmo.simon.ui.theme.idleGreen
import be.helmo.simon.ui.theme.idleRed
import be.helmo.simon.ui.theme.idleYellow

class ColorSelector(
    val idleColor: Color,
    val activeColor: Color
) {

    companion object {

        fun getColor(gameTileEnum: Char) : ColorSelector {
            return when (gameTileEnum) {
                'R' -> ColorSelector(idleRed, activeRed)
                'Y' -> ColorSelector(idleYellow, activeYellow)
                'B' -> ColorSelector(idleBlue, activeBlue)
                'G' -> ColorSelector(idleGreen, activeGreen)
                else -> ColorSelector(Color.White, Color.White)
            }
        }
    }

}