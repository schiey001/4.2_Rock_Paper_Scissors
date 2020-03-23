package com.example.rockpaperscissors.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.GameModel
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<GameModel>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: GameModel) {
            itemView.tvResult.text = game.result
            itemView.tvDate.text = game.date
            when (game.move_computer) {
                "Rock" -> itemView.imgComputer.setImageResource(R.drawable.rock)
                "Paper" -> itemView.imgComputer.setImageResource(R.drawable.paper)
                "Scissors" -> itemView.imgComputer.setImageResource(R.drawable.scissors)
            }

            when (game.move_user) {
                "Rock" -> itemView.imgPlayer.setImageResource(R.drawable.rock)
                "Paper" -> itemView.imgPlayer.setImageResource(R.drawable.paper)
                "Scissors" -> itemView.imgPlayer.setImageResource(R.drawable.scissors)
            }
        }
    }
}