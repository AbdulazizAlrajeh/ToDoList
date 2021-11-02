package com.example.todolist.repository

import android.content.Context
import androidx.room.Room
import com.example.todolist.database.ItemModel
import com.example.todolist.database.ToDoDatabase
import java.lang.Exception


private const val databaseName = "To Do List"
class ToDoRepository(context: Context) {
    /*
     * After defined the data entity,the DAO, and the database,
     * you can use the code to create an instance of the database
     * */
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
    fun filters (stutus:String,category:String) = toDoDao.filters(stutus,category)
    fun filterCategory(category:String) = toDoDao.filterCategory(category)
    fun filterStatus (status:String) = toDoDao.filterStatus(status)

    // this companion object for the instantiation of a class to one "single" instance.
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