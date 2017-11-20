package ua.nedz.task.ui

import ua.nedz.task.ui.Command.*

class ConsolePuzzle15UI : Puzzle15UI {
    override fun hints() {
        println("w - move empty cell up")
        println("a - move empty cell left")
        println("s - move empty cell down")
        println("d - move empty cell right")
        println("exit - exit game")
        println("new game - start new game")
    }

    override fun win() {
        println("Congratulations! You solved puzzle 15!")
    }

    override fun printField(field: Array<IntArray>) {
        println("-----------")
        field.forEach {
            println(it.joinToString (separator = "|") {
                when {
                    it == 0 -> "__"
                    it < 10 -> " " + it
                    else -> "" + it
                }
            })
        }
        println("-----------")
    }

    override fun newGame() {
        println("Starting new game!")
    }

    override fun exit() {
        println("Thank you for playing puzzle 15!")
    }

    override fun readCommand() : Command {
        val input = readLine() ?: ""
        return when (input.toLowerCase()) {
            "w" -> UP
            "a" -> LEFT
            "s" -> DOWN
            "d" -> RIGHT
            "exit" -> EXIT
            "new game" -> NEW_GAME
            else -> NONE
        }
    }

}