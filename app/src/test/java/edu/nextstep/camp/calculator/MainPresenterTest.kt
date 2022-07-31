package edu.nextstep.camp.calculator

import com.google.common.base.Verify.verify
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.RecordData
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
    fun `0부터 9까지 숫자 입력 시 정상 노출됨`(numberData: Int) {
        // when
        presenter.inputNumber(numberData)

        // then
        verify { view.showCalculateExpression(numberData.toString()) }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `숫자 입력 후 연산자 입력 시 연산자 정상 노출됨`(operatorData: Operator) {
        // given
        presenter.inputNumber(1)

        // when
        presenter.inputOperator(operatorData)

        // then
        verify { view.showCalculateExpression("1") }
    }

    @Test
    fun `삭제 버튼 클릭 시 마지막 글자 지워짐`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)

        // when
        presenter.removeLastIndexData()

        // then
        verify { view.showCalculateExpression("1") }
    }

    @Test
    fun `식이 완성됐을 때 =을 누르면 계산 결과 노출`() {
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
    fun `미완성된 식에서 = 클릭 시 토스트 노출`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)

        // when
        presenter.calculateInputValue()

        // then
        verify { view.showCompletionOfExpressionDataMessage() }
    }

    @Test
    fun `기록이 보이는 경우 기록 버튼 클릭 시 기록창 미노출`() {
        // when
        presenter.clickCalculatorRecord(true)

        // then
        view.hideRecord()
    }

    @Test
    fun `기록이 있을 때 이전 기록을 불러오면 기록 정상 노출`() {
        // given
        presenter = MainPresenter(view = view, expression = Expression(listOf(1, Operator.Plus, 5)))
        presenter.calculateInputValue()

        // when
        presenter.loadCalculatorRecord()

        // then
        verify { view.loadRecordList(listOf(RecordData("1 + 5", 6))) }
    }

}