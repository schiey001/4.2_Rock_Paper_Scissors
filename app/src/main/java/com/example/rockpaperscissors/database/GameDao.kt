package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.model.GameModel

@Dao
interface GameDao {

    @Query("SELECT * FROM games ORDER BY id DESC")
    suspend fun getAllGames(): List<GameModel>

    @Insert
    suspend fun insertGame(game: GameModel)

    @Delete
    suspend fun deleteGame(game: GameModel)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

}