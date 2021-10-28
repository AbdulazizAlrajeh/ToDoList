package com.example.todolist.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel


class MainFragment : Fragment() {

    private val toDoItems = mutableListOf<ItemModel>()
    private val toDoViewModel : ToDoViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView :ImageView = view.findViewById(R.id.todolist_imageView)
        val displayMyList: Button = view.findViewById(R.id.viewmylist_button)
        var filter_status :Spinner = view.findViewById(R.id.filter_status_spinner)



        displayMyList.setOnClickListener(){
            toDoViewModel.status = filter_status.selectedItem.toString()
            findNavController().navigate(R.id.action_mainFragment_to_displayListFragment)
        }
    }


}