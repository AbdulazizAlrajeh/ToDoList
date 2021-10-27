package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
  @Insert
  suspend fun addItem(itemModel: ItemModel)

  @Query("SELECT * FROM itemmodel")
  fun getItem():LiveData<List<ItemModel>>

  @Delete
  suspend fun deleteItem(itemModel: ItemModel)

    @Update
    suspend fun updateItem (itemModel: ItemModel)

}