package com.example.projetobd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull



@Entity(tableName = "time")
data class time (
    @PrimaryKey(true)
    @ColumnInfo (name="Id")
    val id: Int?,

    @ColumnInfo (name="nome")
    @NotNull
    val nome : String,

    @ColumnInfo (name="local")
    @NotNull
    val local: String,

    @ColumnInfo (name="dono")
    @NotNull
    val dono: String
)