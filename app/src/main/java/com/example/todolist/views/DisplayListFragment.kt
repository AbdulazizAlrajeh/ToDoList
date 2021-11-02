package com.example.todolist.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapters.ToDoListAdapter
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DisplayListFragment : Fragment() {

    private val toDoItems = mutableListOf<ItemModel>()
    private val toDoViewModel : ToDoViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toDoRecycleView: RecyclerView = view.findViewById(R.id.recycleview_todolist_display)
        val addFloatingActionButton: FloatingActionButton = view.findViewById(R.id.addtodolist_button)


        val toDoListAdapter = ToDoListAdapter(toDoItems,toDoViewModel)
        toDoRecycleView.adapter = toDoListAdapter
        // For display list
        toDoViewModel.getItem().observe(viewLifecycleOwner, Observer {
            it?.let {items ->
                toDoItems.clear()
                toDoItems.addAll(items)
                toDoListAdapter.notifyDataSetChanged()

            }
        })



        addFloatingActionButton.setOnClickListener(){
            findNavController().navigate(R.id.action_displayListFragment_to_addListFragment)

        }






    }
}