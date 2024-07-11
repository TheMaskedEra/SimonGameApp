package be.helmo.simon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Score::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class SimonDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        private var INSTANCE: SimonDatabase? = null

        fun create(context: Context) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                SimonDatabase::class.java,
                "simon-game-database")
                .build()
        }

        fun get(): SimonDatabase {
            return INSTANCE ?: throw IllegalArgumentException("The database must be initialized")
        }
    }

}