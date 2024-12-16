package com.fullcreative.randomcity.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val city: String,
    val color: String,
    val dateTime: String
)
