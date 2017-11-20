package ua.nedz.task.fields

class DistanceFieldComplexityEvaluator : FieldComplexityEvaluator {

    override fun complexity(field: Array<IntArray>): Int {
        val n = field.size
        var result = 0
        for (i in 0 until n)
            for (j in 0 until n) {
                val value = field[i][j]
                val target =
                        if (value == 0)
                            n - 1 to n - 1
                        else
                            (value - 1) / n to (value - 1) % n

                result += (target.first - i) * (target.first - i) +
                        (target.second - j) * (target.second - j)
            }
        return result
    }
}