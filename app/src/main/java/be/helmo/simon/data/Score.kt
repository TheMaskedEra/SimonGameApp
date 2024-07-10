package be.helmo.simon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
class Score(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name:String = "" ,
    val score: Int = 0,
    val photo: String? = null,
    val localisation: String? = null
) {

}