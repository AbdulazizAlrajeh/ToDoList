package com.example.todolist.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel


class DetailsItemFragment : Fragment() {

    private val toDoViewModel : ToDoViewModel by activityViewModels()
    private lateinit var selectItemModel : ItemModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var titleDetailsItemTextView :TextView = view.findViewById(R.id.titleedit_textView)
        var descriptionDetailsTextView :TextView = view.findViewById(R.id.descriptionedit_textView)
        var dataDeadlineDetailsTextView:TextView = view.findViewById(R.id.deadlineedit_textView)
        var statusDetiailsTextView :TextView = view.findViewById(R.id.status_textview)
        var editButtonViewDetails:Button = view.findViewById(R.id.edittododetails_button)
        var createdDateTextView:TextView = view.findViewById(R.id.createddata_textView)
        var categoryTextView:TextView = view.findViewById(R.id.cotegory_textView)

        // For display details of item
        toDoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                categoryTextView.text = it.category
                titleDetailsItemTextView.text = it.title
                descriptionDetailsTextView.text = it.description
                dataDeadlineDetailsTextView.text = " Deadline Date: ${ it.deadline }"
                createdDateTextView.text = "created Date: ${ it.dataCreated }"
                statusDetiailsTextView.text = it.status
                selectItemModel = it
            }
        })


        // When need edit item
        editButtonViewDetails.setOnClickListener {

            toDoViewModel.selectedItemMutableLiveData.postValue(selectItemModel)

            findNavController().navigate(R.id.action_detailsItemFragment_to_editItemFragment)
        }

    }

}



