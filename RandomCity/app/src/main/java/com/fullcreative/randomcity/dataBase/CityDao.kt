package com.fullcreative.randomcity.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    suspend fun insertCity(city: CityEntity)

    @Query("SELECT * FROM CityEntity ORDER BY city ASC")
    suspend fun getAllCities(): List<CityEntity>
}
