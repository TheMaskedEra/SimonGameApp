package be.helmo.simon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Score(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name:String,
    val score: Int,
    val date: LocalDateTime = LocalDateTime.now(),
    val photo: String? = null,
    val localisation: String? = null
)