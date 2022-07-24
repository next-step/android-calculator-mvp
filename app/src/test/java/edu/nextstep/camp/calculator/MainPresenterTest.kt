package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.contract.MainContract
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MainPresenterTest {
    private lateinit var presenter: MainContract.MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainContract.MainPresenter(view)
    }

    @ParameterizedTest(name = "#{index}) when {1} is received, displayedText is {0}")
    @MethodSource("provideInputList")
    fun whenInputListReceived_outputIsExpected(userInputActionList: List<ExpressionToken>, expected: String) {
        // given & when
        userInputActionList.forEach(presenter::addExpressionToken)

        // then
        verify { view.displayExpression(expected) }
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

