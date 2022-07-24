import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.MainContractInterface
import edu.nextstep.camp.calculator.domain.MainPresenter
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class MainPresenterTest {

    private lateinit var mPresenter: MainPresenter
    private lateinit var mView: MainContractInterface.View

    @BeforeEach
    fun setUp() {
        mView = mockk(relaxUnitFun = true)
        mPresenter = MainPresenter(mView)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0])
    fun `0부터 9까지 입력 시 정상 노출됨`(inputNumberData: Int) {
        // when
        mPresenter.inputNumber(inputNumberData)

        // then
        if (!(inputNumberData.toString().equals(""))) {
            verify { mView.showCalculateExpression(inputNumberData.toString()) }
        } else {
            // inputNumberData.toString()이 빈 값("")이면
            // 아무것도 실행하지 않기 위해 else문을 비움.
        }
    }

    @ParameterizedTest
    @EnumSource(Operator::class)
    fun `숫자와 연산자가 섞였을 때 연산자 정상 노출됨`(inputOperatorData: Operator) {
        // given
        mPresenter.inputNumber(1)

        // when
        mPresenter.inputOperator(inputOperatorData)

        // then
        val result = Expression.EMPTY + 1 + inputOperatorData
        verify { mView.showCalculateExpression(result.toString()) }
    }

    @Test
    fun `숫자와 연산자가 섞여 있을 때 삭제 버튼을 누르면 마지막 글자가 지워짐`() {
        // given
        mPresenter.inputNumber(1)
        mPresenter.inputOperator(Operator.Minus)

        // when
        mPresenter.removeLastIndexData()

        // then
        verify { mView.showCalculateExpression("1") }
    }

    @Test
    fun `식이 완성되고 등호 버튼을 누르면 결과가 정상 노출됨`() {
        // given
        mPresenter.inputNumber(1)
        mPresenter.inputOperator(Operator.Plus)
        mPresenter.inputNumber(5)

        // when
        mPresenter.calculateInputValue()

        // then
        verify { mView.showCalculateExpression("6") }
    }

    @Test
    fun `미완성된 식이 있는 상태에서 등호 버튼을 누르면 토스트 알림이 노출됨`() {
        // given
        mPresenter.inputNumber(1)
        mPresenter.inputOperator(Operator.Plus)

        // when
        mPresenter.calculateInputValue()

        // then
        verify { mView.showCompletionOfExpressionDataMessage() }
    }

}