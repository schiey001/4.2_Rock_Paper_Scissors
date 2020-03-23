package com.example.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "games")
data class GameModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "move_computer")
    var move_computer: String,

    @ColumnInfo(name = "move_user")
    var move_user: String,

    @ColumnInfo(name = "result")
    var result: String

) : Parcelable