package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class ItemModel(
    var title:String,
    var description:String,
    var deadline:String,
    var category:String,
    var dataCreated:String,
    var status:String ="Open",
    @PrimaryKey (autoGenerate = true)
    var id:Int =0
)
