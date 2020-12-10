package com.example.escaperoom



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.escaperoom.databinding.ActivityTitleScreenBinding

class TitleScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ActivityTitleScreenBinding>(
            inflater, R.layout.activity_title_screen, container, false)


        binding.enterButton.setOnClickListener{ view: View ->
           view.findNavController().navigate(R.id.action_titleScreen_to_gameScreen)
        }
        return binding.root
    }
}