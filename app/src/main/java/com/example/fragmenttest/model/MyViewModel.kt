package com.example.fragmenttest.model
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fragmenttest.database.DatabaseShow
import com.example.fragmenttest.database.Entity
import com.example.fragmenttest.database.Repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    private val shows: LiveData<List<Entity>>

    init {
        val showDao = DatabaseShow.getDatabase(application).showDao()
        repository = Repository(showDao)
        shows = repository.getShows
    }

    fun getShows(): LiveData<List<Entity>>{
        return shows
    }

    fun addShow(showName: String, language: String, summary:String, image: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addShow(Entity(0,showName,language,summary,image))
        }
    }

    fun deleteShow(ID : Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteShow(ID)
        }
    }
}