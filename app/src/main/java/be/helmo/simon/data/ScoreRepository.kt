package be.helmo.simon.data

class ScoreRepository private constructor(val database: SimonDatabase) {

    suspend fun getAll(): List<Score> = database.scoreDao().getAll()

    suspend fun insertScore(score: Score) = database.scoreDao().insertScore(score)

    companion object {
        private var INSTANCE: ScoreRepository? = null

        fun create(database: SimonDatabase) { //TODO Faire ne sorte qu'il n'y ait que get
            INSTANCE = ScoreRepository(database)
        }

        fun get() : ScoreRepository {
            return INSTANCE ?: throw IllegalStateException("ScoreRepository must be initialized")
        }
    }
}