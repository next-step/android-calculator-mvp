package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.domain.contract.MainContract
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
    private lateinit var presenter: MainContract.PresenterImpl
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MainContract.PresenterImpl(view)
    }

    @ParameterizedTest(name = "#{index}) when {1} is received, displayedText is {0}")
    @MethodSource("provideInputList")
    fun whenInputListReceived_outputIsExpected(userInputActionList: List<ExpressionToken>, expected: String) {
        // given
        val expressionSlot = slot<String>()
        every { view.displayExpression(capture(expressionSlot)) } answers { nothing }

        // when
        userInputActionList.forEach {
            presenter.processToken(it)
        }

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo(expected)
        verify { view.displayExpression(actual) }
    }

    @ParameterizedTest(name = "#{index}) displayedText is {0}")
    @MethodSource("provideDisplayedTextList")
    fun setCurrentDisplayedText(text: String) {
        // given
        val expressionSlot = slot<String>()
        every { view.displayExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.setCurrentDisplayedText(text)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo(text)
        verify { view.displayExpression(actual) }
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
        @JvmStatic
        private fun provideDisplayedTextList(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1000110"),
                Arguments.of("1 + 1 - 1 - "),
            )
        }
    }
}

