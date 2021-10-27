package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.models.ToDoViewModel

class ToDoListAdapter(val listToDo:List<ItemModel>,val viewModel: ToDoViewModel)
    :RecyclerView.Adapter<ToDoListAdapter.ViewHolderItem> (){
        class ViewHolderItem(view: View):RecyclerView.ViewHolder(view){

            val titleItemTextView :TextView = view.findViewById(R.id.title_display_textView)
            val descriptionItemTextView:TextView = view.findViewById(R.id.description_display_textView)
            val dateDeadlineTextView :TextView = view.findViewById(R.id.data_display_textView)

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

        holder.itemView.setOnClickListener{
            viewModel.selectedItemMutableLiveData.postValue(positionItem)
            it.findNavController().navigate(R.id.action_displayListFragment_to_detailsItemFragment)
        }
    }

    override fun getItemCount(): Int =listToDo.size
}