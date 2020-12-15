package com.example.escaperoom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.escaperoom.databinding.FragmentFinishPageBinding


class FinishPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFinishPageBinding>(
        inflater, R.layout.fragment_finish_page, container, false)

        binding.replayButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_finishPage_to_homeFragment)
        }

        return binding.root
    }

}
