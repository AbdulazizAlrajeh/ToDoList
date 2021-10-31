package com.example.todolist.views

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel


class EditItemFragment : Fragment() {

    private val toDoViewModel : ToDoViewModel by activityViewModels()
    private lateinit var selectItemModel : ItemModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_item, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var titleDetailsItemTextView : TextView = view.findViewById(R.id.titleedit_edittext)
        var descriptionDetailsTextView : TextView = view.findViewById(R.id.descriptionedit_edittext)
        var dataDeadlineDetailsTextView: TextView = view.findViewById(R.id.deadlinedit_edittext)
        var statusEditSpinner:Spinner = view.findViewById(R.id.edit_spinner)
        var saveEditButtonView :Button = view.findViewById(R.id.saveedit_button)
        var deleteButtonView :Button = view.findViewById(R.id.deleteedit_button)
        toDoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                titleDetailsItemTextView.text = it.title
                descriptionDetailsTextView.text = it.description
                dataDeadlineDetailsTextView.text = it.deadline

                // Convert Array to list , take array from file Strings.xml
                val res = resources.getStringArray(R.array.status).toList()

                statusEditSpinner.setSelection(res.indexOf(it.status))

                selectItemModel = it

            }
        })

        val datePicker = DatePickerDialog(requireActivity())
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->

            dataDeadlineDetailsTextView.setText("$year/$month/$dayOfMonth")
        }



        dataDeadlineDetailsTextView.setOnClickListener {

            datePicker.show()
        }

        saveEditButtonView.setOnClickListener {

            selectItemModel.title = titleDetailsItemTextView.text.toString()
            selectItemModel.description = descriptionDetailsTextView.text.toString()
            selectItemModel.deadline = dataDeadlineDetailsTextView.text.toString()
            selectItemModel.status = statusEditSpinner.selectedItem.toString()
            toDoViewModel.updateItem(selectItemModel)
            findNavController().navigate(R.id.action_editItemFragment_to_displayListFragment)
        }



        deleteButtonView.setOnClickListener {
            toDoViewModel.deleteItem(selectItemModel)
            findNavController().navigate(R.id.action_editItemFragment_to_displayListFragment)
        }
    }

}