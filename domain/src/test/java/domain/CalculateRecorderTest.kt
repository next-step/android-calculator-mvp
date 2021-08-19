package domain

import com.google.common.truth.Truth.assertThat
import com.joseph.domain.*
import org.junit.jupiter.api.Test

class CalculateRecorderTest {

    private val recorder = CalculateRecorder()
    private val calculator = Calculator()

    @Test
    fun `계산 결과를 한 번 기록하면 records에 하나가 추가된다`() {
        // given
        val expression = Expression(listOf(5, Operator.Plus, 4))
        val result = calculator.calculate(expression.toString())

        // when
        val calculatorResult = CalculateRecord(expression, result!!)
        recorder.recordCalculate(calculatorResult)

        // then
        val expected = listOf(CalculateRecord(expression, result))
        assertThat(recorder.records).isEqualTo(expected)
    }

    @Test
    fun `계산 결과를 다섯 번 기록하면 records에 다섯개 추가된다`() {
        // given
        val expression = Expression(listOf(5, Operator.Plus, 4))
        val result = calculator.calculate(expression.toString())

        // when
        val record = CalculateRecord(expression, result!!)
        repeat(5) {
            recorder.recordCalculate(record)
        }

        // then
        val expected = listOf(
            CalculateRecord(expression, result),
            CalculateRecord(expression, result),
            CalculateRecord(expression, result),
            CalculateRecord(expression, result),
            CalculateRecord(expression, result)
        )
        assertThat(recorder.records).isEqualTo(expected)
    }
}