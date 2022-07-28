package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UserInputActionProcessorTest {

    @ParameterizedTest(name = "#{index}) when {1} is received, displayedText is {0}")
    @MethodSource("provideInputList")
    fun whenInputListReceived_outputIsExpected(userInputActionList: List<ExpressionToken>, expected: String) {
        val inputController = ExpressionTokenProcessor()
        var actual = ""
        userInputActionList.forEach {
            actual = when (it) {
                is Operand -> inputController.processNumberInput(it)
                is Operator -> inputController.processOperatorInput(it)
                OtherExpressionToken.DEL -> inputController.delete()
                else -> inputController.evaluate().result
            }
        }
        assertThat(actual).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        private fun provideInputList(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(Operand(5), Operand(8), Operator.DIVISION), "58 ÷ "),
                Arguments.of(listOf(Operator.DIVISION, Operand(5), Operator.SUBTRACTION, Operator.ADDITION), "5 + "),
                Arguments.of(listOf(Operator.SUBTRACTION, Operand(5), Operator.SUBTRACTION, Operator.ADDITION), "-5 + "),
                Arguments.of(listOf(Operator.SUBTRACTION, Operand(5), Operator.SUBTRACTION, Operator.SUBTRACTION, Operand(10)), "-5 - -10"),
            )
        }
    }
}
