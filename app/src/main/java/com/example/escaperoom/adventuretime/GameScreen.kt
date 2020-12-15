package com.example.escaperoom.adventuretime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.escaperoom.R
import com.example.escaperoom.databinding.ActivityGameScreenBinding

// FRAGMENT

class GameScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ActivityGameScreenBinding>(
            inflater,
            R.layout.activity_game_screen, container, false)

        val story = Story(binding)

        binding.choiceButton1.setOnClickListener{
            story.selectPosition(story.nextPosition1)
        }

        binding.choiceButton2.setOnClickListener{
            story.selectPosition(story.nextPosition2)
        }

        binding.choiceButton3.setOnClickListener{
            story.selectPosition(story.nextPosition3)
        }

        binding.choiceButton4.setOnClickListener{
            story.selectPosition(story.nextPosition4)
        }

        story.startingPoint()
        return binding.root
    }

}