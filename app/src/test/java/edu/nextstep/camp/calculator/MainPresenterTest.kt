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

/**
 * Created by link.js on 2022. 07. 21..
 */
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
    fun `0부터 9까지 숫자입력시 화면에 해당 숫자가 잘 보인다`(source: Int) {
        // when : 0부터 9까지 숫자입력시
        presenter.enterNumber(source)

        // then : 화면에 해당 숫자가 잘 보인다
        verify { view.showExpression(source.toString()) }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `숫자가 있는 상황에서 연산자 입력시 해당 연산자가 잘 보인다`(source: Operator) {
        // gives : 숫자가 있는 상황에서
        presenter.enterNumber(1)

        // when : 연산자 입력시
        presenter.enterOperator(source)

        // then : 화면에 해당 연산자가 잘 보인다
        val result = Expression.EMPTY + 1 + source
        verify { view.showExpression(result.toString()) }
    }
}