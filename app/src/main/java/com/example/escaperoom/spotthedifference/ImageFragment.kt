package com.example.escaperoom.spotthedifference

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
import com.example.escaperoom.databinding.FragmentImageBinding
import com.example.escaperoom.databinding.FragmentRoomBinding
import com.example.escaperoom.databinding.FragmentStartBinding


class ImageFragment : Fragment() {

    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentImageBinding>(
            inflater,
            R.layout.fragment_image, container, false)

        binding.d1a.setOnClickListener {
            binding.d1a.setBackgroundResource(R.drawable.tick)
            binding.d1b.setBackgroundResource(R.drawable.tick)
            binding.d1a.isClickable = false
            binding.d1b.isClickable = false
            count++
        }
        binding.d1b.setOnClickListener {
            binding.d1a.setBackgroundResource(R.drawable.tick)
            binding.d1b.setBackgroundResource(R.drawable.tick)
            binding.d1a.isClickable = false
            binding.d1b.isClickable = false
            count++
        }


        binding.d2a.setOnClickListener {
            binding.d2a.setBackgroundResource(R.drawable.tick)
            binding.d2b.setBackgroundResource(R.drawable.tick)
            binding.d2a.isClickable = false
            binding.d2b.isClickable = false
            count++
        }
        binding.d2b.setOnClickListener {
            binding.d2a.setBackgroundResource(R.drawable.tick)
            binding.d2b.setBackgroundResource(R.drawable.tick)
            binding.d2a.isClickable = false
            binding.d2b.isClickable = false
            count++
        }


        binding.d3a.setOnClickListener {
            binding.d3a.setBackgroundResource(R.drawable.tick)
            binding.d3b.setBackgroundResource(R.drawable.tick)
            binding.d3a.isClickable = false
            binding.d3b.isClickable = false
            count++
        }
        binding.d3b.setOnClickListener {
            binding.d3a.setBackgroundResource(R.drawable.tick)
            binding.d3b.setBackgroundResource(R.drawable.tick)
            binding.d3a.isClickable = false
            binding.d3b.isClickable = false
            count++
        }


        binding.d4a.setOnClickListener {
            binding.d4a.setBackgroundResource(R.drawable.tick)
            binding.d4b.setBackgroundResource(R.drawable.tick)
            binding.d4a.isClickable = false
            binding.d4b.isClickable = false
            count++
        }
        binding.d4b.setOnClickListener {
            binding.d4a.setBackgroundResource(R.drawable.tick)
            binding.d4b.setBackgroundResource(R.drawable.tick)
            binding.d4a.isClickable = false
            binding.d4b.isClickable = false
            count++
        }


        binding.d5a.setOnClickListener {
            binding.d5a.setBackgroundResource(R.drawable.tick)
            binding.d5b.setBackgroundResource(R.drawable.tick)
            binding.d5a.isClickable = false
            binding.d5b.isClickable = false
            count++
        }
        binding.d5b.setOnClickListener {
            binding.d5a.setBackgroundResource(R.drawable.tick)
            binding.d5b.setBackgroundResource(R.drawable.tick)
            binding.d5a.isClickable = false
            binding.d5b.isClickable = false
            count++
        }


        binding.d6a.setOnClickListener {
            binding.d6a.setBackgroundResource(R.drawable.tick)
            binding.d6b.setBackgroundResource(R.drawable.tick)
            binding.d6a.isClickable = false
            binding.d6b.isClickable = false
            count++
        }
        binding.d6b.setOnClickListener {
            binding.d6a.setBackgroundResource(R.drawable.tick)
            binding.d6b.setBackgroundResource(R.drawable.tick)
            binding.d6a.isClickable = false
            binding.d6b.isClickable = false
            count++
        }

        if (count == 6) {
            binding.combination.setBackgroundResource(R.drawable.tick)
            binding.combination.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_imageFragment_to_roomFragment)
            }
        }


        return binding.root
    }


}