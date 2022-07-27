package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxUnitFun = true)
        presenter = MainPresenter(view)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    fun `0부터 9까지 입력 시 정상 노출됨`(inputNumberData: Int) {
        // when
        presenter.inputNumber(inputNumberData)

        // then
        if (inputNumberData.toString() != "") verify { view.showCalculateExpression(inputNumberData.toString()) }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `숫자와 연산자가 섞였을 때 연산자 정상 노출됨`(inputOperatorData: Operator) {
        // given
        presenter.inputNumber(1)

        // when
        presenter.inputOperator(inputOperatorData)

        // then
        val result = Expression(listOf("1", "$inputOperatorData"))
        verify { view.showCalculateExpression(result.toString()) }
    }

    @Test
    fun `숫자와 연산자가 섞여 있을 때 삭제 버튼을 누르면 마지막 글자가 지워짐`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Minus)

        // when
        presenter.removeLastIndexData()

        // then
        verify { view.showCalculateExpression("1") }
    }

    @Test
    fun `식이 완성되고 등호 버튼을 누르면 결과가 정상 노출됨`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)
        presenter.inputNumber(5)

        // when
        presenter.calculateInputValue()

        // then
        verify { view.showCalculateExpression("6") }
    }

    @Test
    fun `미완성된 식이 있는 상태에서 등호 버튼을 누르면 토스트 알림이 노출됨`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)

        // when
        presenter.calculateInputValue()

        // then
        verify { view.showCompletionOfExpressionDataMessage() }
    }

}