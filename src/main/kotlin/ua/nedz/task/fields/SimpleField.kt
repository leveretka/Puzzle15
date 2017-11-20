package ua.nedz.task.fields

import java.util.*

val goal = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12),
        intArrayOf(13, 14, 15, 0))

val puzzleSize = 4

class SimpleField : Field {
    var cells: Array<IntArray> = goal.deepArrayCopy()
        private set

    private var emptyCell = puzzleSize - 1 to puzzleSize - 1

    override fun up() {
        moveEmptyCell({it - 1}, {it})
    }

    override fun left() {
        moveEmptyCell({it}, {it - 1})
    }

    override fun right() {
        moveEmptyCell({it}, {it + 1})
    }

    override fun down() {
        moveEmptyCell({it + 1}, {it})
    }

    inline private fun moveEmptyCell(vertical: (x: Int) -> Int, horizontal: (x: Int) -> Int) {
        val (i, j) = emptyCell
        val newI = vertical(i)
        val newJ = horizontal(j)

        if (newI in 0 until puzzleSize && newJ in 0 until puzzleSize) {
            cells.swap(i, j, newI, newJ)
            emptyCell = newI to newJ
        }
    }

    override fun isSolved() = cells contentDeepEquals goal

    override fun currentState() = cells.deepArrayCopy()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleField

        if (!Arrays.deepEquals(cells, other.cells)) return false
        if (emptyCell != other.emptyCell) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(cells)
        result = 31 * result + emptyCell.hashCode()
        return result
    }

}

private fun Array<IntArray>.deepArrayCopy(): Array<IntArray> = Array(4, {this[it].copyOf()})

private fun Array<IntArray>.swap(x: Int, y: Int, x1: Int, y1: Int) {
    val temp = this[x][y]
    this[x][y] = this[x1][y1]
    this[x1][y1] = temp
}
