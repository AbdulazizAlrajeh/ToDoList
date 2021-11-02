package com.example.todolist.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ItemModel
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.launch


class ToDoViewModel: ViewModel() {
    // Getting instance from todorepository with companion object function
    private val todoRepository = ToDoRepository.get()

    var toDoItem = todoRepository.getItem()
    // Declaration two variable for used in filters
    var status: String = "All"
    var category: String = "All"

    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()
    // Add item to database
    fun addItem(title:String, description:String, dateDeadline: String,category:String,dataCreated:String){
        viewModelScope.launch {
            todoRepository.addItem(ItemModel(title, description, dateDeadline,category,dataCreated))
        }
    }
    // Update item
    fun updateItem (itemModel: ItemModel){
        viewModelScope.launch {
            todoRepository.updateItem(itemModel)
        }
    }

    // Delete item from database
    fun deleteItem(itemModel: ItemModel){
        viewModelScope.launch {
            todoRepository.deleteItem(itemModel)
        }
    }
    // Get item from database
    fun getItem() : LiveData<List<ItemModel>>{

        if(status.equals("All") && category.equals("All"))
        {
            return todoRepository.getItem()
        } else if (status.equals("All"))
        {
            Log.d("items", status)
            return todoRepository.filterCategory(category)

        }
        else if (category.equals("All")){
            return todoRepository.filterStatus(status)

        }else
        {
            return todoRepository.filters(status,category)
        }

    }
}

