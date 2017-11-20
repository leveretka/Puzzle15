package ua.nedz.task.fields

import io.kotlintest.matchers.shouldBe
import org.junit.Test

class DistanceFieldComplexityEvaluatorTest {

    @Test
    fun complexityZeroForGoalField() {
        val evaluator = DistanceFieldComplexityEvaluator()

        evaluator.complexity(goal) shouldBe 0
    }

    @Test
    fun calcComplexityForRandomField() {
        val evaluator = DistanceFieldComplexityEvaluator()
        val field = arrayOf(
                intArrayOf(1, 2, 7, 3),
                intArrayOf(5, 11, 0, 4),
                intArrayOf(9, 6, 10, 8),
                intArrayOf(13, 14, 15, 12)
        )

        evaluator.complexity(field) shouldBe 14
    }

    @Test
    fun calcComplexityForBiggerField() {
        val evaluator = DistanceFieldComplexityEvaluator()
        val field = arrayOf(
                intArrayOf(1, 2, 7, 3, 16),
                intArrayOf(5, 11, 0, 4, 17),
                intArrayOf(9, 6, 10, 8, 18),
                intArrayOf(13, 14, 15, 12, 19),
                intArrayOf(24, 23, 22, 21, 20)
        )

        evaluator.complexity(field) shouldBe 140
    }

}