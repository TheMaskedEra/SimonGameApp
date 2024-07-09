package be.helmo.simon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        private var INSTANCE: ScoreDatabase? = null

        fun create(context: Context) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ScoreDatabase::class.java,
                "simon-game-database")
                .build()
        }

        fun get(): ScoreDatabase {
            return INSTANCE ?: throw IllegalArgumentException("The database must be initialized")
        }
    }

}