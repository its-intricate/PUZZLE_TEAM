package com.example.escaperoom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false)

        binding.darkness.setOnClickListener {
            binding.logo.visibility = INVISIBLE
            binding.darkness.visibility = INVISIBLE
            binding.arrow.visibility = VISIBLE
            binding.darkness.isEnabled = false
            binding.enter.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_homeFragment_to_roomFragment)
            }
        }
        return binding.root
    }

}