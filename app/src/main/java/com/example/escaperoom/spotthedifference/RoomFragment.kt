package com.example.escaperoom.spotthedifference

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.R
import com.example.escaperoom.databinding.FragmentRoomBinding


class RoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRoomBinding>(
            inflater,
            R.layout.fragment_room, container, false)
        binding.zoom.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_roomFragment_to_imageFragment)
        }
        return binding.root
    }


}