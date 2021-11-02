package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
/*
 * Marks a method in a Dao annotated class as an insert method.
 * ToDoDao provides the methods that the rest of the app uses to interact with
   data in the ItemModel table.
  */
@Dao
interface ToDoDao {
  // The @Insert annotation allows you to define methods that insert their parameters into the table in the database
  @Insert
  suspend fun addItem(itemModel: ItemModel)
  //  The @Query annotation allows you to  query data from your app's database.
  // This Query for return all item
  @Query("SELECT * FROM itemmodel")
  fun getItem():LiveData<List<ItemModel>>
  // The @Delete annotation allows you to define methods that delete specific rows from a database table.
  @Delete
  suspend fun deleteItem(itemModel: ItemModel)
  // The @Update annotation allows you to define methods that update specific rows in a database table.
  @Update
  suspend fun updateItem (itemModel: ItemModel)
  // This Query for return all item when status equal the input from user and category equal the input from user
  @Query("SELECT * FROM itemmodel WHERE status= :status AND category = :category")
  fun filters(status:String,category:String):LiveData<List<ItemModel>>
  // This Query for return all item when category equal the input from user
  @Query("SELECT * FROM itemmodel WHERE category= :category")
  fun filterCategory(category:String):LiveData<List<ItemModel>>
  // This Query for return all item when status equal the input from user
  @Query("SELECT * FROM itemmodel WHERE status = :status")
  fun filterStatus(status:String):LiveData<List<ItemModel>>


}