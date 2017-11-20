package ua.nedz.task

import com.nhaarman.mockito_kotlin.*
import io.kotlintest.matchers.shouldBe
import org.junit.Test
import org.mockito.Mockito.verify
import ua.nedz.task.fields.Field
import ua.nedz.task.fields.FieldGenerator
import ua.nedz.task.fields.goal
import ua.nedz.task.ui.Command.*
import ua.nedz.task.ui.Puzzle15UI

class Puzzle15Test {
    private val mockedField: Field = mock {
        on{currentState()} doReturn goal
    }
    private val mockedFieldGenerator: FieldGenerator = mock {
        on {generate()} doReturn mockedField
    }

    private val mockedUI: Puzzle15UI = mock()
    private val game = Puzzle15(mockedFieldGenerator, mockedUI)

    @Test
    fun createPuzzle15() {
        game.solved shouldBe false
        verify(mockedFieldGenerator).generate()
    }

    @Test
    fun puzzleSolved() {
        whenever(mockedField.isSolved()).thenReturn(true)
        game.start()

        game.solved shouldBe true
        verify(mockedUI).win()
    }

    @Test
    fun waitForCommandsWhenPuzzleInProgress() {
        whenever(mockedField.isSolved()).thenReturn(false, false, false,  true)
        whenever(mockedUI.readCommand()).thenReturn(NONE)
        game.start()

        verify(mockedUI, times(3)).readCommand()
        verify(mockedUI, times(4)).printField(anyArray())
    }

    @Test
    fun executeCommandsOnFieldWhenPuzzleInProgress() {
        whenever(mockedField.isSolved()).thenReturn(false, false, false, false,  true)
        whenever(mockedUI.readCommand()).thenReturn(UP, LEFT, RIGHT, DOWN)
        val inOrder = inOrder(mockedField)

        game.start()

        verify(mockedUI, times(4)).readCommand()
        verify(mockedUI, times(5)).printField(anyArray())
        inOrder.verify(mockedField).up()
        inOrder.verify(mockedField).left()
        inOrder.verify(mockedField).right()
        inOrder.verify(mockedField).down()

    }

    @Test
    fun exitGameWhenExitCommandReceived() {
        whenever(mockedField.isSolved()).thenReturn(false, false, false, false,  true)
        whenever(mockedUI.readCommand()).thenReturn(EXIT)

        game.start()

        verify(mockedUI, times(1)).readCommand()
        verify(mockedUI).exit()
        verify(mockedUI, times(1)).printField(anyArray())
    }

    @Test
    fun startNewGameWhenNewGameCommandReceived() {
        whenever(mockedField.isSolved()).thenReturn(false, false, false, false,  true)
        whenever(mockedUI.readCommand()).thenReturn(NEW_GAME, UP, UP, UP, UP)

        game.start()

        verify(mockedUI, times(4)).readCommand()
        verify(mockedUI, times(2)).newGame()
        verify(mockedFieldGenerator, times(2)).generate()
        verify(mockedUI, times(5)).printField(anyArray())
    }

    @Test
    fun showHintsWhenCreateNewGame() {
        verify(mockedUI).hints()
    }

}