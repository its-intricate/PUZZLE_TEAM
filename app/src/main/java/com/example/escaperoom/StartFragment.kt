package com.example.escaperoom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(
            inflater, R.layout.fragment_start, container, false)

        binding.switch1.setOnClickListener {
            if (binding.switch1.isChecked) {
                binding.darkness.visibility = INVISIBLE
                binding.paper.isEnabled = false
            } else {
                binding.paper.setOnClickListener { view: View ->
                    view.findNavController().navigate(R.id.action_startFragment_to_anagramFragment)
                }
                binding.darkness.visibility = VISIBLE
                binding.paper.isEnabled = true

            }
        }


        return binding.root
    }

}