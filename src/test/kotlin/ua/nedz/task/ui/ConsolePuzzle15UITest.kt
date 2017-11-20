package ua.nedz.task.ui

import io.kotlintest.matchers.shouldBe
import org.junit.Before
import org.junit.Test
import ua.nedz.task.fields.goal
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ConsolePuzzle15UITest {
    private val ui = ConsolePuzzle15UI()
    private val content = ByteArrayOutputStream()

    @Before
    fun setUp() {
        System.setOut(PrintStream(content))
    }

    @Test
    fun returnExitWhenReceiveExitCommand() {
        System.setIn(ByteArrayInputStream("exit".toByteArray()))
        ui.readCommand() shouldBe Command.EXIT
    }

    @Test
    fun returnDirectionsForWASD() {
        System.setIn(ByteArrayInputStream("W\nA\nS\nD".toByteArray()))
        ui.readCommand() shouldBe Command.UP
        ui.readCommand() shouldBe Command.LEFT
        ui.readCommand() shouldBe Command.DOWN
        ui.readCommand() shouldBe Command.RIGHT
    }

    @Test
    fun returnNewGameCommand() {
        System.setIn(ByteArrayInputStream("new game".toByteArray()))
        ui.readCommand() shouldBe Command.NEW_GAME
    }

    @Test
    fun returnNoneCommandForImproperCommands() {
        System.setIn(ByteArrayInputStream("sfdghd df".toByteArray()))
        ui.readCommand() shouldBe Command.NONE
    }

    @Test
    fun properMessageWhenExit() {
        ui.exit()
        content.toString() shouldBe "Thank you for playing puzzle 15!\n"
    }

    @Test
    fun properMessageWhenNewGame() {
        ui.newGame()
        content.toString() shouldBe "Starting new game!\n"
    }

    @Test
    fun printGoalFieldRepresentation() {
        val goalString = "-----------\n" +
                " 1| 2| 3| 4\n" +
                " 5| 6| 7| 8\n" +
                " 9|10|11|12\n" +
                "13|14|15|__\n" +
                "-----------\n"
        ui.printField(goal)

        content.toString() shouldBe goalString
    }

    @Test
    fun printSimpleFieldRepresentation() {
        val expectedString = "-----------\n" +
                "15|14|13|12\n" +
                " 5| 6| 7| 8\n" +
                " 4| 3| 2|__\n" +
                " 9|10|11| 1\n" +
                "-----------\n"

        val field = arrayOf(
                intArrayOf(15, 14, 13, 12),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(4, 3, 2, 0),
                intArrayOf(9, 10, 11, 1)
        )
        ui.printField(field)

        content.toString() shouldBe expectedString
    }

    @Test
    fun printWinningMessage() {
        ui.win()

        content.toString() shouldBe "Congratulations! You solved puzzle 15!\n"
    }

    @Test
    fun printHints() {
        ui.hints()

        val expectedMessage = "w - move empty cell up\n" +
                "a - move empty cell left\n" +
                "s - move empty cell down\n" +
                "d - move empty cell right\n" +
                "exit - exit game\n" +
                "new game - start new game\n"

        content.toString() shouldBe expectedMessage
    }

}