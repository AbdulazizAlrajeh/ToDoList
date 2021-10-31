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
    private val todoRepository = ToDoRepository.get()

    var toDoItem = todoRepository.getItem()

    var status: String = "All"
    var category: String = "All"

    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()

    fun addItem(title:String, description:String, dateDeadline: String,category:String,dataCreated:String){
        viewModelScope.launch {
            todoRepository.addItem(ItemModel(title, description, dateDeadline,category,dataCreated))
        }
    }

    fun updateItem (itemModel: ItemModel){
        viewModelScope.launch {
            todoRepository.updateItem(itemModel)
        }
    }


    fun deleteItem(itemModel: ItemModel){
        viewModelScope.launch {
            todoRepository.deleteItem(itemModel)
        }
    }

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

