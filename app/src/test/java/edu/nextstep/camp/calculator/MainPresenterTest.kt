package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import io.mockk.Called
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.verify
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

        verify(exactly = 1) { mainMockActivity.setResultTextView("1") }
        verify(exactly = 1) { mainMockActivity.setResultTextView("10") }
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

        verify(exactly = 1) { mainMockActivity.setResultTextView("1 +") }
        verify(exactly = 1) { mainMockActivity.setResultTextView("1") }
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
}