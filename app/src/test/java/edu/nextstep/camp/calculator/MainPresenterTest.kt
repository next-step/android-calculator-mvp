package edu.nextstep.camp.calculator

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
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
}