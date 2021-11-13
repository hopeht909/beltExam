package com.example.fragmenttest.database

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface DaoShow {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShow(item: Entity)

    @Query("SELECT * FROM ShowTable ORDER BY id ASC")
    fun getShows(): LiveData<List<Entity>>


    @Query("Delete from ShowTable where ID=:showID")
    suspend fun deleteShow(showID: Int)

}