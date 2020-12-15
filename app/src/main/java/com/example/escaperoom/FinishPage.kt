package com.example.escaperoom


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.escaperoom.databinding.FragmentFinishPageBinding


class FinishPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFinishPageBinding>(
        inflater, R.layout.fragment_finish_page, container, false)
        return binding.root
    }

}
