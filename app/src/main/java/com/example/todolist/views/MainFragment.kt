package com.example.todolist.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.todolist.R


class MainFragment : Fragment() {


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

        displayMyList.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_displayListFragment)
        }
    }


}