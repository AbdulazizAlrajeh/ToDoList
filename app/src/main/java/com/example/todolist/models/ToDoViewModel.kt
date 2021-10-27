package com.example.todolist.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ItemModel
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.launch


class ToDoViewModel: ViewModel() {
    private val todoRepository = ToDoRepository.get()

    var toDoItem = todoRepository.getItem()

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
}