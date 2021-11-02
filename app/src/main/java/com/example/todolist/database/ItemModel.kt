package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
/*
 * The data class code defines a ItemModel data entity.
 * Each instance of ItemModel represents a row in a ItemModel table in the app's database.
 * */

@Entity
data class ItemModel(
    var title:String,
    var description:String,
    var deadline:String,
    var category:String,
    var dataCreated:String,
    var status:String ="Open",

   /* Each Room entity must define a primary key that uniquely identifies
    each row in the database table.*/

    @PrimaryKey (autoGenerate = true)
    var id:Int =0
)
