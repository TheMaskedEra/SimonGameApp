package be.helmo.simon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM Score ORDER BY date DESC")
    suspend fun getAll(): List<Score>

    @Query("SELECT * FROM Score ORDER BY score DESC") //TODO changer en list ? Comment trouver les trois premiers
    suspend fun getBestScores(): List<Score>

    @Insert
    suspend fun insertScore(score: Score)
}