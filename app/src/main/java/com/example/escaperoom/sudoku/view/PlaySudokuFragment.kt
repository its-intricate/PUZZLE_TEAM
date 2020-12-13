package com.example.escaperoom.sudoku.view

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.escaperoom.R
import com.example.escaperoom.sudoku.game.Cell
import com.example.escaperoom.databinding.FragmentPlaySudokuBinding
import com.example.escaperoom.sudoku.view.custom.SudokuBoardView
import com.example.escaperoom.sudoku.viewmodel.PlaySudokuViewModel
import kotlinx.android.synthetic.main.fragment_play_sudoku.*


class PlaySudokuFragment : Fragment(), SudokuBoardView.OnTouchListener {

    private lateinit var viewModel: PlaySudokuViewModel
    private lateinit var numberButtons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlaySudokuBinding>(
            inflater,
            R.layout.fragment_play_sudoku, container, false)

        binding.game = this
        super.onCreate(savedInstanceState)

        binding.sudokuBoardView.registerListener(this)

        viewModel = ViewModelProviders.of(this).get(PlaySudokuViewModel::class.java)
        viewModel.sudokuGame.selectedCellLiveData.observe(viewLifecycleOwner, Observer { updateSelectedCellUI(it) })
        viewModel.sudokuGame.cellsLiveData.observe(viewLifecycleOwner, Observer { updateCells(it) })
        viewModel.sudokuGame.isTakingNotesLiveData.observe(viewLifecycleOwner, Observer { updateNoteTakingUI(it) })
        viewModel.sudokuGame.highlightedKeysLiveData.observe( viewLifecycleOwner, Observer { updateHighlightedKeys(it) })

        numberButtons = listOf(binding.oneButton, binding.twoButton, binding.threeButton,
            binding.fourButton, binding.fiveButton, binding.sixButton,
            binding.sevenButton, binding.eightButton, binding.nineButton)

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.sudokuGame.handleInput(index + 1)
            }
        }

        binding.notesButton.setOnClickListener { viewModel.sudokuGame.changeNoteTakingState() }
        binding.deleteButton.setOnClickListener { viewModel.sudokuGame.delete() }
        binding.exitButton.setOnClickListener { viewModel.sudokuGame.exit() }
        binding.completeButton.setOnClickListener { viewModel.sudokuGame.complete(binding) }

        return binding.root
    }

    private fun updateCells(cells: List<Cell>?) = cells?.let {
        if (viewModel.sudokuGame.isGameComplete()) { completeButton.visibility = View.VISIBLE } else { completeButton.visibility = View.INVISIBLE }
            sudokuBoardView.updateCells(cells)
    }

    private fun updateSelectedCellUI(cell: Pair<Int, Int>) = cell?.let {
        sudokuBoardView.updateSelectedCellUI(cell.first, cell.second)
    }

    private fun updateNoteTakingUI(isNoteTaking: Boolean?) = isNoteTaking?.let {
        val color = if (it) {
            this.context?.let {it1 ->
            ContextCompat.getColor(
                it1,
                R.color.design_default_color_primary
            )}
        } else Color.LTGRAY
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            notesButton.background.colorFilter =
                color?.let { it1 -> BlendModeColorFilter(it1, BlendMode.MULTIPLY) }
        } else {
            if (color != null) {
                notesButton.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
            }
        }
    }

    private fun updateHighlightedKeys(set: Set<Int>?) = set?.let {
        numberButtons.forEachIndexed { index, button ->
            val color = if (set.contains(index + 1)) {
                this.context?.let { it1 ->
                    ContextCompat.getColor(
                        it1,
                        R.color.design_default_color_on_primary)
                }
            } else {
                Color.LTGRAY
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                button.background.colorFilter =
                    color?.let { it1 -> BlendModeColorFilter(it1, BlendMode.MULTIPLY) }

            } else {
                if (color != null) {
                    button.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
                }
            }
        }
    }

    override fun onCellTouched(row: Int, col: Int){
        viewModel.sudokuGame.updateSelectedCell(row, col)
    }
}
