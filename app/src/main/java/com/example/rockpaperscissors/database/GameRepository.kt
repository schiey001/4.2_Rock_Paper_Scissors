package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.GameModel

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<GameModel> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: GameModel) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: GameModel) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }
}
