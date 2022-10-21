package com.marmistrz.openmeteo.database

import androidx.room.Entity
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.PrimaryKey
import androidx.room.Insert

@Entity
data class Location(@PrimaryKey val name: String, val row: Int, val column: Int)

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun all(): List<Location>

    @Insert
    fun insert(location: Location)
}

@Database(entities = [Location::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
