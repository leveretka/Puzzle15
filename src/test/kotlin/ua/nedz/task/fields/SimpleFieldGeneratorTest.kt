package ua.nedz.task.fields

import io.kotlintest.matchers.beGreaterThanOrEqualTo
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.mock.mock
import org.junit.Test

class SimpleFieldGeneratorTest {

    @Test
    fun generateSimpleField() {
        val mockedComplexityEvaluator: FieldComplexityEvaluator = mock()

        val generator = SimpleFieldGenerator(mockedComplexityEvaluator)
        generator.generate() shouldBe SimpleField()
    }

    @Test
    fun generateFieldWithMetrics() {
        val complexityEvaluator = DistanceFieldComplexityEvaluator()
        val generator = SimpleFieldGenerator(complexityEvaluator)

        val field = generator.generate()

        complexityEvaluator.complexity(field.currentState()) should beGreaterThanOrEqualTo(complexityEvaluator.complexityLevel)

    }

}