package com.example.todolist.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
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

class ToDoListAdapter(val listToDo:List<ItemModel>,val viewModel: ToDoViewModel)
    :RecyclerView.Adapter<ToDoListAdapter.ViewHolderItem> (){
        class ViewHolderItem(view: View):RecyclerView.ViewHolder(view){

            val titleItemTextView :TextView = view.findViewById(R.id.title_display_textView)
            val descriptionItemTextView:TextView = view.findViewById(R.id.description_display_textView)
            val dateDeadlineTextView :TextView = view.findViewById(R.id.data_display_textView)
            val statusTextView:TextView = view.findViewById(R.id.status_textView)
            val cardView :CardView = view.findViewById(R.id.item_cardview)

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
        holder.dateDeadlineTextView.text = positionItem.deadline
        holder.statusTextView.text = positionItem.status
        var status = holder.statusTextView.text.toString()



        var currentDate = Date()
        val format = SimpleDateFormat("yyyy/MM/dd")
        val date = format.parse(positionItem.deadline)

        if (currentDate > date)
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#090910"))
            holder.statusTextView.text = "task is past the date"
        }else
        {
            when (status) {
                "Open" -> holder.cardView.setBackgroundColor(Color.parseColor("#0A9396"))
                "In process" -> holder.cardView.setBackgroundColor(Color.parseColor("#FFD60A"))
                "Done" -> holder.cardView.setBackgroundColor(Color.parseColor("#AE2012"))
            }

        }
        holder.itemView.setOnClickListener{
            viewModel.selectedItemMutableLiveData.postValue(positionItem)
            it.findNavController().navigate(R.id.action_displayListFragment_to_detailsItemFragment)
        }
    }

    override fun getItemCount(): Int =listToDo.size
}