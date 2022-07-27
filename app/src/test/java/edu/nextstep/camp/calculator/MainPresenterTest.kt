package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
<<<<<<< HEAD
import edu.nextstep.camp.calculator.domain.RecordData
=======
>>>>>>> 545fb3c (feat: 피드백 반영)
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
<<<<<<< HEAD
    fun `0부터 9까지 입력 시 숫자 정상 노출됨`(inputNumberData: Int) {
=======
    fun `0부터 9까지 입력 시 정상 노출됨`(inputNumberData: Int) {
>>>>>>> 545fb3c (feat: 피드백 반영)
        // when
        presenter.inputNumber(inputNumberData)

        // then
<<<<<<< HEAD
        verify { view.showCalculateExpression(inputNumberData.toString()) }
=======
        if (inputNumberData.toString() != "") verify { view.showCalculateExpression(inputNumberData.toString()) }
>>>>>>> 545fb3c (feat: 피드백 반영)
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
<<<<<<< HEAD
    fun `연산자 입력 시 연산자 정상 노출`(inputOperatorData: Operator) {
=======
    fun `숫자와 연산자가 섞였을 때 연산자 정상 노출됨`(inputOperatorData: Operator) {
>>>>>>> 545fb3c (feat: 피드백 반영)
        // given
        presenter.inputNumber(1)

        // when
        presenter.inputOperator(inputOperatorData)

        // then
<<<<<<< HEAD
        verify { view.showCalculateExpression("1") }
    }

    @Test
    fun `삭제 버튼을 누르면 마지막 글자가 지워짐`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)
=======
        val result = Expression(listOf("1", "$inputOperatorData"))
        verify { view.showCalculateExpression(result.toString()) }
    }

    @Test
    fun `숫자와 연산자가 섞여 있을 때 삭제 버튼을 누르면 마지막 글자가 지워짐`() {
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Minus)
>>>>>>> 545fb3c (feat: 피드백 반영)

        // when
        presenter.removeLastIndexData()

        // then
        verify { view.showCalculateExpression("1") }
    }

    @Test
<<<<<<< HEAD
    fun `=을 누르면 계산결과가 나옴`() {
=======
    fun `식이 완성되고 등호 버튼을 누르면 결과가 정상 노출됨`() {
>>>>>>> 545fb3c (feat: 피드백 반영)
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
<<<<<<< HEAD
    fun `계산식이 정상적이지 않을 때 =를 누르면 토스트 노출`() {
=======
    fun `미완성된 식이 있는 상태에서 등호 버튼을 누르면 토스트 알림이 노출됨`() {
>>>>>>> 545fb3c (feat: 피드백 반영)
        // given
        presenter.inputNumber(1)
        presenter.inputOperator(Operator.Plus)

        // when
        presenter.calculateInputValue()

        // then
        verify { view.showCompletionOfExpressionDataMessage() }
    }

<<<<<<< HEAD
    @Test
    fun `기록창 노출 시 기록 버튼을 누르면 기록창 미노출`() {
        // when
        presenter.clickRecord(true)

        // then
        verify { view.hideRecord() }
    }

    @Test
    fun `기록을 불러오는 경우 기록 정상 노출`() {
        // given
        presenter = MainPresenter(view = view, expression = Expression(listOf(1, Operator.Plus, 5)))
        presenter.calculateInputValue()

        // when
        presenter.loadRecordData()

        // then : 기록을 잘 보여준다.
        verify { view.loadRecordList(listOf(RecordData("1 + 5", 6))) }
    }
=======
>>>>>>> 545fb3c (feat: 피드백 반영)
}