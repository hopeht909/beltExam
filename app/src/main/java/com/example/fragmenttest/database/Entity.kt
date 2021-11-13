package com.example.fragmenttest.database




import androidx.room.Entity
import androidx.room.PrimaryKey


//table name
@Entity(tableName = "ShowTable")
data class Entity(

    // table contents
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val showName: String,
    val language: String,
    val summary:String,
    val image: String
)