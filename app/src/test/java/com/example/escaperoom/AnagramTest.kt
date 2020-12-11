package com.example.escaperoom

import com.example.escaperoom.anagram.AnagramFragment
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.*
import org.junit.Test

class AnagramTest{
    private val anagram = AnagramFragment()

    @Test
    fun random_word_is_returned() {
        anagram.newGame()
        assertThat(anagram.correctWord, instanceOf(String::class.java))
    }

    @Test
    fun word_characters_are_shuffled() {
        anagram.newGame()
        val word = anagram.correctWord
        val result = anagram.scrambledWord
        assertEquals(word.toCharArray().sort(), result.toCharArray().sort())
    }



}

