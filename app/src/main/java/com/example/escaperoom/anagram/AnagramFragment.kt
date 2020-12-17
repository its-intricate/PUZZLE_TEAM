package com.example.escaperoom.anagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import java.util.*
import kotlin.random.Random
import androidx.databinding.DataBindingUtil
import com.example.escaperoom.R
import com.example.escaperoom.databinding.FragmentAnagramBinding


class AnagramFragment : Fragment() {

    private val wordList = arrayOf(
        "DIM", "BLEAK", "DARKNESS", "SWITCH", "LIGHT", "CLARITY", "BLACK",
        "PHOSPHOR", "ULTRAVIOLET", "SHADOW", "MOON", "SUN", "STARS", "FIRE", "FLAME", "TORCH",
        "ILLUMINATE", "BEAM", "WEATHER", "VISION"
    )

    private fun randomWord(): String {
        return wordList[Random.nextInt(wordList.size)]
    }

    private fun shuffleWord(word: String): String {
        if ("" != word) {
            val a = word.toCharArray()
            for (i in a.indices) {
                val j: Int = Random.nextInt(a.size)
                val tmp = a[i]
                a[i] = a[j]
                a[j] = tmp
            }
            return String(a)
        }
        return word
    }

    fun newGame() {
        correctWord = randomWord()
        if (correctWord == currentWord){
            newGame()
        }
        currentWord = correctWord
        scrambledWord = shuffleWord(correctWord)
    }

    private var currentWord: String = ""
    var correctWord: String = ""
    var scrambledWord: String = ""
    private var count = 1
    private val lockId = intArrayOf(
        R.id.lock1,
        R.id.lock2,
        R.id.lock3
    )
    private val unlockId = intArrayOf(
        R.id.unlock1,
        R.id.unlock2,
        R.id.unlock3
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAnagramBinding>(
            inflater,
            R.layout.fragment_anagram, container, false)
        newGame()
        binding.game = this
        val input = binding.editText

        binding.submitButton.setOnClickListener {
            view : View ->
            var answer = input.text.toString().toUpperCase(Locale.ROOT)
            if (validate(answer)) {
                Toast.makeText(this.context, "Congratulations! You found the word $correctWord", Toast.LENGTH_LONG
                ).show()
                unlock(binding)
                count++
                if (count <= 3) {
                    newGame()
                    binding.invalidateAll()
                } else {
                    view.findNavController().navigate(R.id.action_anagramFragment_to_nextRoomFragment)
                }
            } else {
                Toast.makeText(this.context, "Retry !", Toast.LENGTH_SHORT).show()
            }
            input.setText("")
        }
        return binding.root
    }

     private fun unlock(binding: FragmentAnagramBinding) {
         when (count) {
             1 -> {
                 binding.lock1.visibility = INVISIBLE
                 binding.unlock1.visibility = VISIBLE
             }
             2 -> {
                 binding.lock2.visibility = INVISIBLE
                 binding.unlock2.visibility = VISIBLE
             }
             3 -> {
                 binding.lock3.visibility = INVISIBLE
                 binding.unlock3.visibility = VISIBLE
             }
         }
    }


    private fun validate(answer: String): Boolean {
        return correctWord == answer
    }

}