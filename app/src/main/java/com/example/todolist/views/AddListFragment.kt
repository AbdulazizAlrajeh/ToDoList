package com.example.todolist.views

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.models.ToDoViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class AddListFragment : Fragment() {

    private val toDoViewModel : ToDoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addTitle:EditText = view.findViewById(R.id.titleadd_edittext)
        val addDescription:EditText = view.findViewById(R.id.descriptionadd_edittext)
        val addDeadline :EditText = view.findViewById(R.id.deadlineadd_edittext)
        val addSave :Button = view.findViewById(R.id.saveaddd_button)


        val datePicker = DatePickerDialog(requireActivity())
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->

            addDeadline.setText("$dayOfMonth / $month / $year")
        }



        addDeadline.setOnClickListener {

            datePicker.show()
        }

        addSave.setOnClickListener(){
            var titleName = addTitle.text.toString()
            var description = addDescription.text.toString()
            var deadline = "${datePicker.datePicker.dayOfMonth} / ${datePicker.datePicker.month} / ${datePicker.datePicker.year}"



            toDoViewModel.addItem(titleName,description,deadline)
            findNavController().popBackStack()

        }
    }
}