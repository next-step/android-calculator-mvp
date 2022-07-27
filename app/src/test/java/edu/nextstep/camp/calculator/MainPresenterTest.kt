package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import edu.nextstep.camp.calculator.view.MemoryUIModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainPresenterTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainPresenter: MainPresenter

    @RelaxedMockK
    private lateinit var mainMockActivity: MainContract.View

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        mainPresenter = MainPresenter(mainMockActivity)
    }

    @Test
    fun 숫자_버튼을_누를때_view_함수를_잘_호출하는가() {
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickNumberButton(0)

        verifyAll {
            mainMockActivity.setResultTextView("1")
            mainMockActivity.setResultTextView("10")
        }
    }

    @Test
    fun operator_버튼을_누를때_view에_함수를_잘_호출하는가() {
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickOperandButton("+")
        mainPresenter.onClickNumberButton(0)

        verify(exactly = 1) { mainMockActivity.setResultTextView("1 +") }
    }

    @Test
    fun 사용자가_수식을_입력하고_삭제_버튼을_누를때_view에_함수를_잘_호출하는가() {
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickOperandButton("+")
        mainPresenter.onClickNumberButton(0)

        mainPresenter.onClickDeleteButton()
        mainPresenter.onClickDeleteButton()

        verify { mainMockActivity.setResultTextView("1") }
    }

    @Test
    fun 사용자가_수식을_완전하게_입력하고_equals를_누르면_결과를_잘_도출하는가() {
        //given
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickOperandButton("+")
        mainPresenter.onClickNumberButton(5)

        //when
        mainPresenter.onClickEqualButton()

        verify(exactly = 1) { mainMockActivity.setResultTextView("6") }
    }

    @Test
    fun `eqauls_버튼_누를때마다_계산_기록에_저장되었을 때 시계_버튼을_누르면_계산_기록을_보이는 함수가 호출된다`() {
        //given
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickOperandButton("+")
        mainPresenter.onClickNumberButton(5)
        mainPresenter.onClickEqualButton()
        val list = listOf(MemoryUIModel(0, "1 + 5", "= 6"))

        //when
        mainPresenter.onClickMemoryButton()

        //then
        verify { mainMockActivity.showExpressionMemoryView(list) }
    }

    @Test
    fun 계산_기록_UI가_떠_있는_상태에서_시계_버튼을_누르면_계산_기록_UI가_사라지는_함수가_호출된다() {
        //given
        mainPresenter.onClickNumberButton(1)
        mainPresenter.onClickOperandButton("+")
        mainPresenter.onClickNumberButton(5)
        mainPresenter.onClickEqualButton()

        //when
        mainPresenter.onClickMemoryButton()
        mainPresenter.onClickMemoryButton()

        //then
        verify { mainMockActivity.hideExpressionMemoryView() }
    }
}