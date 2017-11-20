package ua.nedz.task

import ua.nedz.task.fields.Field
import ua.nedz.task.fields.FieldGenerator
import ua.nedz.task.fields.SimpleFieldGenerator
import ua.nedz.task.ui.Command
import ua.nedz.task.ui.Command.*
import ua.nedz.task.ui.ConsolePuzzle15UI
import ua.nedz.task.ui.Puzzle15UI

fun main(args: Array<String>) {
    Puzzle15().start()
}

class Puzzle15(private val fieldGenerator: FieldGenerator = SimpleFieldGenerator(), private val ui: Puzzle15UI = ConsolePuzzle15UI()) {

    var solved = false
        private set

    private var exitSent = false
    private var field: Field = fieldGenerator.generate()

    init {
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
                field = fieldGenerator.generate()
            }
            EXIT -> {
                ui.exit()
                exitSent = true
            }
            NONE -> {}
        }
    }

}
