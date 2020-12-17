package com.example.escaperoom.anagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.R
import com.example.escaperoom.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(
            inflater,
            R.layout.fragment_start, container, false)
        pageView(binding)
        binding.switch1.setOnClickListener {
            if (binding.switch1.isChecked) {
                pageView(binding)
                binding.darkness.visibility = INVISIBLE
            } else {
                binding.paper.setOnClickListener { view: View ->
                    view.findNavController().navigate(R.id.action_startFragment_to_anagramFragment)
                }
                binding.darkness.visibility = VISIBLE
            }
        }

        binding.picture.setOnClickListener { view: View ->

            view.findNavController().navigate(R.id.action_startFragment_to_picturePuzzle)
        }
        return binding.root
    }

    private fun pageView(binding: FragmentStartBinding) {
        return binding.paper.setOnClickListener {
            binding.page.visibility = VISIBLE
            binding.page.isEnabled = true
            binding.switch1.visibility = INVISIBLE
            binding.page.setOnClickListener {
                binding.page.visibility = INVISIBLE
                binding.page.isEnabled = false
                binding.switch1.visibility = VISIBLE
            }
        }
    }

}