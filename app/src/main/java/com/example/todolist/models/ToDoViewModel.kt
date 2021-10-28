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

    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()

    fun addItem(title:String, description:String, dateDeadline: String){
        viewModelScope.launch {
            todoRepository.addItem(ItemModel(title, description, dateDeadline))
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

        if(status.equals("All"))
        {
            return todoRepository.getItem()
        } else
        {
            Log.d("items", status)
            return todoRepository.filterStutus(status)

        }


    }
}