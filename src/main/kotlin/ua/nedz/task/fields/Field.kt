package ua.nedz.task.fields

interface Field {

    fun shuffle()
    fun isSolved() : Boolean
    fun up()
    fun left()
    fun right()
    fun down()
    fun currentState() : Array<IntArray>

}