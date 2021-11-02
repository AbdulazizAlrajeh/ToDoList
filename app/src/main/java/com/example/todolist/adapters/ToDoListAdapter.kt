package com.example.todolist.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ToDoListAdapter(val listToDo:List<ItemModel>,val viewModel: ToDoViewModel,val context: Context)
    :RecyclerView.Adapter<ToDoListAdapter.ViewHolderItem> (){

        class ViewHolderItem(view: View):RecyclerView.ViewHolder(view){

            val titleItemTextView :TextView = view.findViewById(R.id.title_display_textView)
            val descriptionItemTextView:TextView = view.findViewById(R.id.description_display_textView)
            val dateDeadlineTextView :TextView = view.findViewById(R.id.data_display_textView)
            val statusTextView:Spinner = view.findViewById(R.id.status_textView)
            val cardView :CardView = view.findViewById(R.id.display_linearLayout)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
       val positionItem = listToDo[position]
        holder.titleItemTextView.text = positionItem.title
        holder.descriptionItemTextView.text = positionItem.description
        holder.dateDeadlineTextView.text = "Deadline At: ${ positionItem.deadline }"

        val adapter = ArrayAdapter.createFromResource(
            context,
            R.array.status,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.statusTextView.adapter = adapter
        holder.statusTextView.setSelection(context.resources.getStringArray(R.array.status).indexOf(positionItem.status))
        var selected = false

        holder.statusTextView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (selected){
                    positionItem.status = adapter.getItem(position).toString()
                    viewModel.updateItem(positionItem)
                }
                selected = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }
        }

        // This variable for take current date
        var currentDate = Date()
        // Format for create date the same format I declare
        val format = SimpleDateFormat("yyyy/MM/dd")
        val date = format.parse(positionItem.deadline)

        if (currentDate > date)
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d9cab3"))

        }else
        {
            when (positionItem.status) {
                "Open" -> holder.cardView.setCardBackgroundColor(Color.parseColor("#9dcee2"))
                "In process" -> holder.cardView.setCardBackgroundColor(Color.parseColor("#4091c9"))
                "Done" -> holder.cardView.setCardBackgroundColor(Color.parseColor("#1368aa"))
            }

        }
        // post value to liveData to send data from the Display list fragment to Details item fragment
        holder.itemView.setOnClickListener{
            viewModel.selectedItemMutableLiveData.postValue(positionItem)
            it.findNavController().navigate(R.id.action_displayListFragment_to_detailsItemFragment)
        }
    }
    // Calls this method to get the size of the data set.
    override fun getItemCount(): Int =listToDo.size
}