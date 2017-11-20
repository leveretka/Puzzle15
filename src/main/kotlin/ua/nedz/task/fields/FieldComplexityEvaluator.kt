package ua.nedz.task.fields

interface FieldComplexityEvaluator {
    fun complexity (field: Array<IntArray>): Int
}