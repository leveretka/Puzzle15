package ua.nedz.task.ui

interface Puzzle15UI {
    fun readCommand() : Command
    fun exit()
    fun newGame()
    fun printField(field: Array<IntArray>)
    fun win()
    fun hints()
}

enum class Command {
    UP, DOWN, RIGHT, LEFT, EXIT, NEW_GAME, NONE
}