package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @ParameterizedTest(name = "#{index}) when {1} is received, displayedText is {0}")
    @MethodSource("provideInputList")
    fun whenInputListReceived_outputIsExpected(userInputActionList: List<ExpressionToken>, expected: String) {
        // given & when
        userInputActionList.forEach {
            when (it) {
                is Operand -> presenter.addOperandToken(it)
                is Operator -> presenter.addOperatorToken(it)
                OtherExpressionToken.DEL -> presenter.delete()
                else -> presenter.evaluate()
            }
        }

        // then
        verify { view.displayExpression(expected) }
    }

    @ParameterizedTest(name = "#{index}) when {1} is received, history is {0}")
    @MethodSource("provideInputListAndHistory")
    fun givenInputList_whenHistoryBtnClicked_historyIsExpected(userInputActionList: List<ExpressionToken>, expected: List<EvaluationRecord>) {
        // given & when
        userInputActionList.forEach {
            when (it) {
                is Operand -> presenter.addOperandToken(it)
                is Operator -> presenter.addOperatorToken(it)
                OtherExpressionToken.DEL -> presenter.delete()
                else -> presenter.evaluate()
            }
        }

        presenter.toggleEvaluationHistory()

        // then
        verify { view.showEvaluationHistory(expected) }
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
        private fun provideInputListAndHistory(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(Operand(5), Operand(8), Operator.DIVISION), emptyList<EvaluationRecord>()),
                Arguments.of(
                    listOf(Operand(5), Operator.ADDITION, Operand(5), OtherExpressionToken.EQUALS,
                    Operand(1), Operator.SUBTRACTION, Operand(12), OtherExpressionToken.EQUALS),
                    listOf(EvaluationRecord("5 + 5", "10"), EvaluationRecord("101 - 12", "89"))),
            )
        }
    }
}

