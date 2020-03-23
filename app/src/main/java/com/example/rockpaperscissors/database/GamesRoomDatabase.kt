package com.example.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissors.model.GameModel

@Database(entities = [GameModel::class], version = 1, exportSchema = false)
abstract class GamesRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAMES_DATABASE"

        @Volatile
        private var gamesRoomDatabaseInstance: GamesRoomDatabase? = null

        fun getDatabase(context: Context): GamesRoomDatabase? {
            if (gamesRoomDatabaseInstance == null) {
                synchronized(GamesRoomDatabase::class.java) {
                    if (gamesRoomDatabaseInstance == null) {
                        gamesRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,GamesRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gamesRoomDatabaseInstance
        }
    }

}
