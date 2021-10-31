package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemModel::class],version = 5)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}