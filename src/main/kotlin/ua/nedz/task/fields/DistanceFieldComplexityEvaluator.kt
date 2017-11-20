package ua.nedz.task.fields

class DistanceFieldComplexityEvaluator : FieldComplexityEvaluator {

    override val complexityLevel = 20

    override fun complexity(field: Array<IntArray>): Int {
        val size = field.size
        var result = 0
        for (i in 0 until size)
            for (j in 0 until size) {
                val currentValue = field[i][j]
                val targetPosition = calcTargetPosition(currentValue, size)

                result += calcDistance(targetPosition, i, j)
            }
        return result
    }

    private fun calcDistance(targetPosition: Pair<Int, Int>, i: Int, j: Int) =
            (targetPosition.first - i) * (targetPosition.first - i) +
                    (targetPosition.second - j) * (targetPosition.second - j)

    private fun calcTargetPosition(currentValue: Int, size: Int): Pair<Int, Int> {
        return if (currentValue == 0)
            size - 1 to size - 1
        else
            (currentValue - 1) / size to (currentValue - 1) % size
    }
}