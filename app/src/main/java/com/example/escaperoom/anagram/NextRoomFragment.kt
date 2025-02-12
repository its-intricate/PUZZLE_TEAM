package com.example.escaperoom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.databinding.FragmentNextRoomBinding


class NextRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNextRoomBinding>(
            inflater, R.layout.fragment_next_room, container, false)

        binding.end.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_nextRoomFragment_to_escapeRoom)
        }
        return binding.root
    }

}