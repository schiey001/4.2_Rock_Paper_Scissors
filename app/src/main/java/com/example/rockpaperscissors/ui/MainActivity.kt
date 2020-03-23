package com.example.rockpaperscissors.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.GameModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Rock, Paper, Scissors"

        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        imgRock.setOnClickListener { playGame("Rock") }
        imgPaper.setOnClickListener { playGame("Paper") }
        imgScissors.setOnClickListener { playGame("Scissors") }

        btnShowHistory.setOnClickListener { startHistoryActivity() }
    }

    private fun startHistoryActivity() {
        val intent = Intent(this, GameHistoryActivity::class.java)
        startActivity(intent)
    }

//    private fun getShoppingListFromDatabase() {
//        mainScope.launch {
//            val games = withContext(Dispatchers.IO) {
//                gameRepository.getAllGames()
//            }
//            this@MainActivity.games.clear()
//            this@MainActivity.games.addAll(games)
//            this@MainActivity.gameAdapter.notifyDataSetChanged()
//        }
//    }

    private fun playGame(playerAction: String) {
        val computerAction = getComputerAction()
        val result = getResult(computerAction, playerAction)
        val currentDate = getCurrentDateTime()
        mainScope.launch {
            val game = GameModel(
                date = currentDate.toString("yyyy/MM/dd HH:mm:ss"),
                move_computer = computerAction,
                move_user = playerAction,
                result = result
            )

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
            when (computerAction) {
                "Rock" -> imgMoveComputer.setImageResource(R.drawable.rock)
                "Paper" -> imgMoveComputer.setImageResource(R.drawable.paper)
                "Scissors" -> imgMoveComputer.setImageResource(R.drawable.scissors)
            }

            when (playerAction) {
                "Rock" -> imgMovePlayer.setImageResource(R.drawable.rock)
                "Paper" -> imgMovePlayer.setImageResource(R.drawable.paper)
                "Scissors" -> imgMovePlayer.setImageResource(R.drawable.scissors)
            }

            when (result) {
                "Win" -> tvResult.text = "You Win!"
                "Draw" -> tvResult.text = "It is a draw, try again."
                "Lose" -> tvResult.text = "You Lose!"
            }
//            getShoppingListFromDatabase()
        }
    }

    private fun getComputerAction(): String {
        var computerAction = ""
        when ((1 until 4).random()) {
            1 -> {computerAction = "Rock"}
            2 -> {computerAction = "Paper"}
            3 -> {computerAction = "Scissors"}
            else -> {

            }
        }
        return computerAction
    }

    private fun getResult(computerAction: String, playerAction: String): String {
        var result = ""
        when (computerAction) {
            "Rock" -> {
                when (playerAction) {
                    "Rock" -> {
                        result = "Draw"
                    }
                    "Paper" -> {
                        result = "Win"
                    }
                    "Scissors" -> {
                        result = "Lose"
                    }
                }
            }
            "Paper" -> {
                when (playerAction) {
                    "Rock" -> {
                        result = "Lose"
                    }
                    "Paper" -> {
                        result = "Draw"
                    }
                    "Scissors" -> {
                        result = "Win"
                    }
                }
            }
            "Scissors" -> {
                when (playerAction) {
                    "Rock" -> {
                        result = "Win"
                    }
                    "Paper" -> {
                        result = "Lose"
                    }
                    "Scissors" -> {
                        result = "Draw"
                    }
                }
            }
        }
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear_history -> {
                clearHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearHistory(){
        tvResult.text = ""
        imgMovePlayer.setImageResource(R.drawable.rockpaperscissors)
        imgMoveComputer.setImageResource(R.drawable.rockpaperscissors)
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}
