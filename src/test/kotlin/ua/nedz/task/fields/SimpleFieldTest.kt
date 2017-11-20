package ua.nedz.task.fields

import io.kotlintest.matchers.*
import org.junit.Test

class SimpleFieldTest {
    private val field = SimpleField()

    @Test
    fun createField() {
        field.cells contentEquals goal
        field.isSolved() shouldBe true
    }

    @Test
    fun testGoingUpWhenPossible() {

        val resultCells = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 0),
                intArrayOf(13, 14, 15, 12)
        )

        field.up()

        field.cells contentEquals resultCells
    }

    @Test
    fun testNotGoingUpWhenImpossible() {

        field.up()
        field.up()
        field.up()

        val upImpossibleState = field.cells.copyOf()

        field.up()

        field.cells shouldBe upImpossibleState
    }

    @Test
    fun testGoingLeftWhenPossible() {

        val resultCells = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12),
                intArrayOf(13, 14, 0, 15)
        )

        field.left()

        field.cells contentEquals resultCells
    }

    @Test
    fun testNotGoingLeftWhenImpossible() {

        field.left()
        field.left()
        field.left()

        val leftImpossibleState = field.cells.copyOf()

        field.left()

        field.cells shouldBe leftImpossibleState
    }

    @Test
    fun testNotGoingRightWhenImpossible() {
        field.right()

        field.cells contentEquals goal
    }

    @Test
    fun testNotGoingDownWhenImpossible() {
        field.down()

        field.cells contentEquals goal
    }

    @Test
    fun testGoingInMultipleDirections() {
        val expected = arrayOf(
                intArrayOf(1, 2, 7, 3),
                intArrayOf(5, 11, 0, 4),
                intArrayOf(9, 6, 10, 8),
                intArrayOf(13, 14, 15, 12)
        )

        field.up()
        field.up()
        field.up()
        field.left()
        field.down()
        field.down()
        field.left()
        field.up()
        field.right()

        field.cells contentEquals expected
        field.isSolved() shouldBe false
    }

    @Test
    fun testShuffle() {
        val complexityEvaluator = DistanceFieldComplexityEvaluator()

        field.shuffle()
        field.isSolved() shouldBe false
        complexityEvaluator.complexity(field.cells) should beGreaterThanOrEqualTo(complexityLevel)
    }

    @Test
    fun testReturnCopyOfCells() {
        field.cells shouldNotBe field.currentState()
        field.cells contentEquals field.currentState()

    }

}