package ua.nedz.task.fields

import java.util.*

val goal = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12),
        intArrayOf(13, 14, 15, 0))

val rand = Random(System.currentTimeMillis())

val complexityLevel = 20

val n = 4

class SimpleField (private val complexityEvaluator: FieldComplexityEvaluator = DistanceFieldComplexityEvaluator()) : Field {
    var cells: Array<IntArray> = goal.deepArrayCopy()
        private set

    private var emptyCell = n - 1 to n - 1

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

        if (newI !in 0 until n || newJ !in 0 until n)
            return
        cells.swap(i, j, newI, newJ)
        emptyCell = newI to newJ
    }

    override fun isSolved() = cells contentDeepEquals goal

    override fun shuffle() {
        cells = goal.deepArrayCopy()
        emptyCell = n - 1 to n - 1
        while (complexityEvaluator.complexity(cells) < complexityLevel) {
            when (rand.nextInt(3)) {
                0 -> up()
                1 -> down()
                2 -> left()
                3 -> right()
            }

        }
    }

    override fun currentState() = cells.deepArrayCopy()

}

private fun Array<IntArray>.deepArrayCopy(): Array<IntArray> = Array(4, {this[it].copyOf()})

private fun Array<IntArray>.swap(x: Int, y: Int, x1: Int, y1: Int) {
    val temp = this[x][y]
    this[x][y] = this[x1][y1]
    this[x1][y1] = temp
}
