package com.example.escaperoom.sudoku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.escaperoom.sudoku.game.SudokuGame

class PlaySudokuViewModel : ViewModel() {
    val sudokuGame = SudokuGame()
}