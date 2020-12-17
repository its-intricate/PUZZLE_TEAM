package com.example.escaperoom.picturePuzzle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.escaperoom.picturePuzzle.GestureDetectGridView.OnSwipeListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.escaperoom.R
import com.example.escaperoom.databinding.PuzzlePictureBinding
import kotlinx.android.synthetic.main.puzzle_picture.*
import java.util.*

enum class SwipeDirections {
    UP, DOWN, LEFT, RIGHT
}

class PicturePuzzle : Fragment() {

    companion object {
        private const val TOTAL_COLUMNS = 3
        private const val DIMENSIONS = TOTAL_COLUMNS * TOTAL_COLUMNS

        private var boardColumnWidth = 0
        private var boardColumnHeight = 0
    }

    private val tileListIndexes = mutableListOf<Int>()

    private val isSolved: Boolean
        get() {
            var solved = false
            for (i in tileListIndexes.indices) {
                if (tileListIndexes[i] == i) {
                    solved = true
                } else {
                    solved = false
                    break
                }
            }

            return solved
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<PuzzlePictureBinding>(
                inflater,
                R.layout.puzzle_picture,
                container,
                false
        )
        binding.game = this

        super.onCreate(savedInstanceState)

        init(binding)
        scrambleTileBoard(binding)
        setTileBoardDimensions(binding)

        return binding.root
    }

    private fun init(binding: PuzzlePictureBinding) {

        binding.gestureDetectGridView.apply {
            numColumns = TOTAL_COLUMNS
            setOnSwipeListener(object : OnSwipeListener {
                override fun onSwipe(direction: SwipeDirections, position: Int) {
                    moveTiles(binding, direction, position)
                }
            })
        }

        tileListIndexes += 0 until DIMENSIONS
    }

    private fun scrambleTileBoard(binding: PuzzlePictureBinding) {
        var index: Int
        var tempIndex: Int
        val random = Random()

        for (i in tileListIndexes.size - 1 downTo 1) {
            index = random.nextInt(i + 1)
            tempIndex = tileListIndexes[index]
            tileListIndexes[index] = tileListIndexes[i]
            tileListIndexes[i] = tempIndex
        }
    }

    private fun setTileBoardDimensions(binding: PuzzlePictureBinding) {
        val observer = binding.gestureDetectGridView.viewTreeObserver
        observer.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.gestureDetectGridView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val displayWidth = binding.gestureDetectGridView.measuredWidth
                val displayHeight = binding.gestureDetectGridView.measuredHeight
                val statusbarHeight = binding.game?.context?.let { getStatusBarHeight(it) }
                val requiredHeight = displayHeight - statusbarHeight!!

                boardColumnWidth = displayWidth / TOTAL_COLUMNS
                boardColumnHeight = requiredHeight / TOTAL_COLUMNS

                displayTileBoard(binding)
            }
        })
    }

    private fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }

        return result
    }

    /**
     * Used for both init and every time a new swap move is made by the user.
     */
    private fun displayTileBoard(binding: PuzzlePictureBinding) {
        val tileImages = mutableListOf<ImageView>()
        var tileImage: ImageView

        tileListIndexes.forEach { i ->
            tileImage = ImageView(binding.game?.context)

            when (i) {
                0 -> tileImage.setBackgroundResource(R.drawable.p1)
                1 -> tileImage.setBackgroundResource(R.drawable.p2)
                2 -> tileImage.setBackgroundResource(R.drawable.p3)
                3 -> tileImage.setBackgroundResource(R.drawable.p4)
                4 -> tileImage.setBackgroundResource(R.drawable.p5)
                5 -> tileImage.setBackgroundResource(R.drawable.p6)
                6 -> tileImage.setBackgroundResource(R.drawable.p7)
                7 -> tileImage.setBackgroundResource(R.drawable.p8)
                8 -> tileImage.setBackgroundResource(R.drawable.p9)
            }

            tileImages.add(tileImage)
        }

        gesture_detect_grid_view.adapter = TileImageAdapter(tileImages, boardColumnWidth, boardColumnHeight)
    }

    private fun displayToast(binding: PuzzlePictureBinding ,@StringRes textResId: Int) {
        Toast.makeText(binding.game?.context, getString(textResId), Toast.LENGTH_SHORT).show()
    }

    private fun moveTiles(binding: PuzzlePictureBinding, direction: SwipeDirections, position: Int) {
        // Upper-left-corner tile
        if (position == 0) {
            when (direction) {
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                SwipeDirections.DOWN -> swapTile(binding, position, TOTAL_COLUMNS)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Upper-center tiles
        } else if (position > 0 && position < TOTAL_COLUMNS - 1) {
            when (direction) {
                SwipeDirections.LEFT -> swapTile(binding, position, -1)
                SwipeDirections.DOWN -> swapTile(binding, position, TOTAL_COLUMNS)
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Upper-right-corner tile
        } else if (position == TOTAL_COLUMNS - 1) {
            when (direction) {
                SwipeDirections.LEFT -> swapTile(binding, position, -1)
                SwipeDirections.DOWN -> swapTile(binding, position, TOTAL_COLUMNS)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Left-side tiles
        } else if (position > TOTAL_COLUMNS - 1 && position < DIMENSIONS - TOTAL_COLUMNS && position % TOTAL_COLUMNS == 0) {
            when (direction) {
                SwipeDirections.UP -> swapTile(binding, position, -TOTAL_COLUMNS)
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                SwipeDirections.DOWN -> swapTile(binding, position, TOTAL_COLUMNS)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Right-side AND bottom-right-corner tiles
        } else if (position == TOTAL_COLUMNS * 2 - 1 || position == TOTAL_COLUMNS * 3 - 1) {
            when (direction) {
                SwipeDirections.UP -> swapTile(binding, position, -TOTAL_COLUMNS)
                SwipeDirections.LEFT -> swapTile(binding, position, -1)
                SwipeDirections.DOWN -> {
                    // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                    // right-corner tile.
                    if (position <= DIMENSIONS - TOTAL_COLUMNS - 1) {
                        swapTile(binding, position, TOTAL_COLUMNS)
                    } else {
                        displayToast(binding, R.string.invalid_move)
                    }
                }
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Bottom-left corner tile
        } else if (position == DIMENSIONS - TOTAL_COLUMNS) {
            when (direction) {
                SwipeDirections.UP -> swapTile(binding, position, -TOTAL_COLUMNS)
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - TOTAL_COLUMNS) {
            when (direction) {
                SwipeDirections.UP -> swapTile(binding, position, -TOTAL_COLUMNS)
                SwipeDirections.LEFT -> swapTile(binding, position, -1)
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                else -> displayToast(binding, R.string.invalid_move)
            }
            // Center tiles
        } else {
            when (direction) {
                SwipeDirections.UP -> swapTile(binding, position, -TOTAL_COLUMNS)
                SwipeDirections.LEFT -> swapTile(binding, position, -1)
                SwipeDirections.RIGHT -> swapTile(binding, position, 1)
                else -> swapTile(binding, position, TOTAL_COLUMNS)
            }
        }
    }

    private fun swapTile(binding: PuzzlePictureBinding , currentPosition: Int, swap: Int) {
        val newPosition = tileListIndexes[currentPosition + swap]
        tileListIndexes[currentPosition + swap] = tileListIndexes[currentPosition]
        tileListIndexes[currentPosition] = newPosition
        displayTileBoard(binding)

        if (isSolved) {
            binding.clue.visibility = VISIBLE
            binding.button.visibility = VISIBLE
            displayToast(binding, R.string.winner)
            binding.button.setOnClickListener {view: View ->
                view?.findNavController()?.navigate(R.id.action_picturePuzzle_to_startFragment)
            }
        }
    }
}