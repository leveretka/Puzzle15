package ua.nedz.task

import ua.nedz.task.fields.Field
import ua.nedz.task.fields.SimpleField
import ua.nedz.task.ui.Command
import ua.nedz.task.ui.Command.*
import ua.nedz.task.ui.ConsolePuzzle15UI
import ua.nedz.task.ui.Puzzle15UI

fun main(args: Array<String>) {
    Puzzle15().start()
}

class Puzzle15(private val field: Field = SimpleField(), private val ui: Puzzle15UI = ConsolePuzzle15UI()) {

    var solved = true
        private set

    private var exitSent = false

    init {
        solved = false
        field.shuffle()
        ui.hints()
    }

    fun start() {
        ui.newGame()
        while (!field.isSolved()) {
            ui.printField(field.currentState())
            resolveCommand(ui.readCommand())

            if (exitSent) return
        }

        solutionFound()
    }

    private fun solutionFound() {
        solved = true
        ui.printField(field.currentState())
        ui.win()
    }

    private fun resolveCommand(command: Command) {
        when (command) {
            UP -> field.up()
            LEFT -> field.left()
            DOWN -> field.down()
            RIGHT -> field.right()
            NEW_GAME -> {
                ui.newGame()
                field.shuffle()
            }
            EXIT -> {
                ui.exit()
                exitSent = true
            }
            else -> {}
        }
    }

}
