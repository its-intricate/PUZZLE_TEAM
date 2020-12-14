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
import java.util.*


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
            hintMessage(binding)
        }
        binding.d1b.setOnClickListener {
            binding.d1a.setBackgroundResource(R.drawable.tick)
            binding.d1b.setBackgroundResource(R.drawable.tick)
            binding.d1a.isClickable = false
            binding.d1b.isClickable = false
            count++
            hintMessage(binding)
        }


        binding.d2a.setOnClickListener {
            binding.d2a.setBackgroundResource(R.drawable.tick)
            binding.d2b.setBackgroundResource(R.drawable.tick)
            binding.d2a.isClickable = false
            binding.d2b.isClickable = false
            count++
            hintMessage(binding)
        }
        binding.d2b.setOnClickListener {
            binding.d2a.setBackgroundResource(R.drawable.tick)
            binding.d2b.setBackgroundResource(R.drawable.tick)
            binding.d2a.isClickable = false
            binding.d2b.isClickable = false
            count++
            hintMessage(binding)
        }


        binding.d3a.setOnClickListener {
            binding.d3a.setBackgroundResource(R.drawable.tick)
            binding.d3b.setBackgroundResource(R.drawable.tick)
            binding.d3a.isClickable = false
            binding.d3b.isClickable = false
            count++
            hintMessage(binding)
        }
        binding.d3b.setOnClickListener {
            binding.d3a.setBackgroundResource(R.drawable.tick)
            binding.d3b.setBackgroundResource(R.drawable.tick)
            binding.d3a.isClickable = false
            binding.d3b.isClickable = false
            count++
            hintMessage(binding)
        }


        binding.d4a.setOnClickListener {
            binding.d4a.setBackgroundResource(R.drawable.tick)
            binding.d4b.setBackgroundResource(R.drawable.tick)
            binding.d4a.isClickable = false
            binding.d4b.isClickable = false
            count++
            hintMessage(binding)
        }
        binding.d4b.setOnClickListener {
            binding.d4a.setBackgroundResource(R.drawable.tick)
            binding.d4b.setBackgroundResource(R.drawable.tick)
            binding.d4a.isClickable = false
            binding.d4b.isClickable = false
            count++
            hintMessage(binding)
        }


        binding.d5a.setOnClickListener {
            binding.d5a.setBackgroundResource(R.drawable.tick)
            binding.d5b.setBackgroundResource(R.drawable.tick)
            binding.d5a.isClickable = false
            binding.d5b.isClickable = false
            count++
            hintMessage(binding)
        }
        binding.d5b.setOnClickListener {
            binding.d5a.setBackgroundResource(R.drawable.tick)
            binding.d5b.setBackgroundResource(R.drawable.tick)
            binding.d5a.isClickable = false
            binding.d5b.isClickable = false
            count++
            hintMessage(binding)
        }


        binding.d6a.setOnClickListener {
            binding.d6a.setBackgroundResource(R.drawable.tick)
            binding.d6b.setBackgroundResource(R.drawable.tick)
            binding.d6a.isClickable = false
            binding.d6b.isClickable = false
            count++
            hintMessage(binding)
        }
        binding.d6b.setOnClickListener {
            binding.d6a.setBackgroundResource(R.drawable.tick)
            binding.d6b.setBackgroundResource(R.drawable.tick)
            binding.d6a.isClickable = false
            binding.d6b.isClickable = false
            count++
            hintMessage(binding)
        }

        return binding.root
    }

    private fun hintMessage(binding: FragmentImageBinding) {
        if (count == 6) {
            binding.combination.visibility = VISIBLE
            binding.imageView2.visibility = INVISIBLE
            binding.imageView3.visibility = INVISIBLE
            binding.d1a.visibility = INVISIBLE
            binding.d1b.visibility = INVISIBLE
            binding.d2a.visibility = INVISIBLE
            binding.d2b.visibility = INVISIBLE
            binding.d3a.visibility = INVISIBLE
            binding.d3b.visibility = INVISIBLE
            binding.d4a.visibility = INVISIBLE
            binding.d4b.visibility = INVISIBLE
            binding.d5a.visibility = INVISIBLE
            binding.d5b.visibility = INVISIBLE
            binding.d6a.visibility = INVISIBLE
            binding.d6b.visibility = INVISIBLE
            binding.safe.visibility = VISIBLE
            binding.safe.setOnClickListener {
                binding.darkness.visibility = VISIBLE
                binding.editText.visibility = VISIBLE
                binding.submitButton.visibility = VISIBLE
                binding.submitButton.setOnClickListener{ view: View ->
                    var combination = binding.editText.text.toString().toUpperCase(Locale.ROOT)
                    if (validate(combination)) {
                        view.findNavController().navigate(R.id.action_imageFragment_to_startFragment)
                    } else {
                        binding.editText.setText("")
                    }
                }


            }


        }
    }

    private fun validate(combination: String): Boolean {
        return combination == "4268"
    }


}