package ua.nedz.task.fields

interface FieldComplexityEvaluator {
    val complexityLevel: Int

    fun complexity (field: Array<IntArray>): Int
}