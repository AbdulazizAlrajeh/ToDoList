package com.example.todolist.repository

import android.content.Context
import androidx.room.Room
import com.example.todolist.database.ItemModel
import com.example.todolist.database.ToDoDatabase
import java.lang.Exception


private const val databaseName = "To Do List"
class ToDoRepository(context: Context) {
    private val database:ToDoDatabase =
        Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            databaseName
        ).fallbackToDestructiveMigration().build()

    private val toDoDao = database.toDoDao()

    fun getItem() = toDoDao.getItem()
    suspend fun addItem (itemModel: ItemModel) = toDoDao.addItem(itemModel)
    suspend fun deleteItem(itemModel: ItemModel) = toDoDao.deleteItem(itemModel)
    suspend fun updateItem (itemModel: ItemModel) = toDoDao.updateItem(itemModel)


    companion object{
        private var instance :ToDoRepository? = null

        fun init(context: Context){
            if (instance == null)
                instance = ToDoRepository(context)

        }
        fun get():ToDoRepository{
            return instance ?:throw Exception("Inventory Repository must be initialise")
        }
    }
}