package com.example.escaperoom.sudoku.game

import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.escaperoom.R
import com.example.escaperoom.databinding.FragmentPlaySudokuBinding

class SudokuGame {
    var selectedCellLiveData = MutableLiveData<Pair<Int, Int>>()
    var cellsLiveData = MutableLiveData<List<Cell>>()
    val isTakingNotesLiveData = MutableLiveData<Boolean>()
    val highlightedKeysLiveData = MutableLiveData<Set<Int>>()

    private var selectedRow = -1
    private var selectedColumn = -1
    private var isTakingNotes = false

    private var board: Board

    init {
        var cells = List(9 * 9) {i -> Cell(i / 9, i % 9, 0)}
        cells = setStartingState(cells)
        board = Board(9, cells)

        selectedCellLiveData.postValue(Pair(selectedRow, selectedColumn))
        cellsLiveData.postValue(board.cells)
        isTakingNotesLiveData.postValue(isTakingNotes)
    }

    fun handleInput(number: Int) {
        if (selectedRow == -1 || selectedColumn == -1) return
        val cell = board.getCell(selectedRow, selectedColumn)
        if (cell.isStartingCell) return

        if (isTakingNotes) {
            if (cell.notes.contains(number)) {
                cell.notes.remove(number)
            } else {
                cell.notes.add(number)
            }
            highlightedKeysLiveData.postValue(cell.notes)
        } else {
            cell.value = number
        }
        cellsLiveData.postValue(board.cells)
    }

    fun updateSelectedCell(row: Int, col: Int) {
        val cell = board.getCell(row, col)
        if(!cell.isStartingCell) {
            selectedRow = row
            selectedColumn = col
            selectedCellLiveData.postValue(Pair(row, col))

            if (isTakingNotes) {
                highlightedKeysLiveData.postValue(cell.notes)
            }
        }
    }

    fun changeNoteTakingState() {
        if (selectedRow == -1 || selectedColumn == -1) return
        val cell = board.getCell(selectedRow, selectedColumn)

        isTakingNotes = !isTakingNotes
        isTakingNotesLiveData.postValue(isTakingNotes)

        val curNotes = if (isTakingNotes) {
            cell.notes
        } else {
            setOf<Int>()
        }
        highlightedKeysLiveData.postValue(curNotes)
    }

    fun delete() {
        if (selectedRow == -1 || selectedColumn == -1) return
        val cell = board.getCell(selectedRow, selectedColumn)
        if (isTakingNotes) {
            cell.notes.clear()
            highlightedKeysLiveData.postValue(setOf())
        } else {
            cell.value = 0
        }
        cellsLiveData.postValue(board.cells)
    }

    fun exit(binding: FragmentPlaySudokuBinding) {
        /*
        var cells = List(9 * 9) {i -> Cell(i / 9, i % 9, 0)}
        cells = setStartingState(cells)
        board = Board(9, cells)

        selectedCellLiveData.postValue(Pair(selectedRow, selectedColumn))
        cellsLiveData.postValue(board.cells)
        isTakingNotesLiveData.postValue(isTakingNotes)
        */
        Navigation.findNavController(binding.exitButton).navigate(R.id.action_playSudokuFragment_to_escapeRoom)

    }

    fun complete(binding: FragmentPlaySudokuBinding) {
        Navigation.findNavController(binding.completeButton).navigate(R.id.action_playSudokuFragment_to_finishPage)
    }

    fun isGameComplete(): Boolean {
        var sumRow = 0
        for (r in 0 until board.size ) {
                sumRow = 0
            for (c in 0 until board.size ) {
                sumRow += board.getCell(r, c).value
            }
            if (sumRow != 45) return false
        }

        var sumCol = 0
        for (c in 0 until board.size ) {
            sumCol = 0
            for (r in 0 until board.size ) {
                sumCol += board.getCell(r, c).value
            }
            if (sumCol != 45) return false
        }
        return true
    }

    fun setStartingState (cells: List<Cell>): List<Cell> {
        cells[1].value = 7
        cells[5].value = 9
        cells[7].value = 4
        cells[9].value = 2
        cells[11].value = 3
        cells[12].value = 1
        cells[13].value = 4
        cells[17].value = 6
        cells[25].value = 3
        cells[27].value = 8
        cells[34].value = 7
        cells[37].value = 1
        cells[39].value = 5
        cells[40].value = 6
        cells[41].value = 2
        cells[43].value = 9
        cells[46].value = 5
        cells[53].value = 4
        cells[55].value = 4
        cells[63].value = 7
        cells[67].value = 2
        cells[68].value = 4
        cells[69].value = 8
        cells[71].value = 1
        cells[73].value = 6
        cells[75].value = 7
        cells[79].value = 2

        cells[0].value = 6
        cells[2].value = 1
        cells[3].value = 3
        cells[4].value = 8
        cells[6].value = 5
        cells[8].value = 2
        cells[10].value = 9
        cells[14].value = 5
        cells[15].value = 7
        cells[16].value = 8
        cells[18].value = 5
        cells[19].value = 8
        cells[20].value = 4
        cells[21].value = 2
        cells[22].value = 7
        cells[23].value = 6
        cells[24].value = 1
        cells[26].value = 9
        cells[80].value = 3
        cells[78].value = 4
        cells[77].value = 1
        cells[76].value = 5
        cells[74].value = 8
        cells[72].value = 9
        cells[70].value = 6
        cells[66].value = 9
        cells[65].value = 5
        cells[64].value = 3
        cells[62].value = 7
        cells[61].value = 5
        cells[60].value = 9
        cells[59].value = 8
        cells[58].value = 3
        cells[57].value = 6
        cells[56].value = 2
        cells[54].value = 1



        for (cell in cells) {
            if (cell.value != 0) cell.isStartingCell = true
        }

        return cells
    }

}