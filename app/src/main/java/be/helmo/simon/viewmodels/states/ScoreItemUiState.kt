package be.helmo.simon.viewmodels.states

import java.time.LocalDateTime

data class ScoreItemUiState(
    val score: Int,
    val name: String,
    val date: LocalDateTime,
    val photo: String? = null,
    val localisation: String? = null
)