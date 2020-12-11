package com.example.escaperoom.adventuretime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.R
import com.example.escaperoom.databinding.ActivityEscapeRoomBinding


class EscapeRoom : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ActivityEscapeRoomBinding>(
            inflater,
            R.layout.activity_escape_room, container, false)

        binding.vase.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.action_escapeRoom_to_titleScreen)
        }

        return binding.root
    }
}