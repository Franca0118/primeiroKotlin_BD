package com.example.projetobd

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull



@Entity(tableName = "jogador")
data class jogador (
    @PrimaryKey(true)
    @ColumnInfo (name="Id")
    val id: Int?,

    @ColumnInfo (name="nome")
    val nome : String,

    @ColumnInfo (name="camisa")
    val camisa: String,

    @ColumnInfo (name="time")
    val time: String,

    @ColumnInfo (name="imgurl")
    val imgurl: String
)
