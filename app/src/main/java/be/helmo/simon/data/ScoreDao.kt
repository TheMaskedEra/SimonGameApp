package be.helmo.simon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score")
    suspend fun getAll(): List<Score>

    @Insert
    suspend fun insertScore(score: Score)
}