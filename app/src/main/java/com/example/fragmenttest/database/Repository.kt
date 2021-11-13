package com.example.fragmenttest.database

import androidx.lifecycle.LiveData


class Repository(private val showDao: DaoShow) {

    val getShows: LiveData<List<Entity>> = showDao.getShows()

    suspend fun addShow(item: Entity){
        showDao.addShow(item)
    }


    suspend fun deleteShow(showID : Int){
        showDao.deleteShow(showID)
    }

}