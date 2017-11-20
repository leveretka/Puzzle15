package ua.nedz.task.fields

import java.util.*

class SimpleFieldGenerator(private val complexityEvaluator: FieldComplexityEvaluator = DistanceFieldComplexityEvaluator()) : FieldGenerator {

    private val rand = Random(System.currentTimeMillis())

    override fun generate() : Field {
        return SimpleField().apply {
            while (complexityEvaluator.complexity(cells) < complexityEvaluator.complexityLevel) {
                when (rand.nextInt(3)) {
                    0 -> up()
                    1 -> down()
                    2 -> left()
                    3 -> right()
                }
            }
        }
    }
}