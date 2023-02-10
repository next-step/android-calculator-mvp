package camp.nextstep.edu.calculator

import com.nextstep.edu.calculator.domain.Calculator
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    override fun callEquals() {}
    override fun callDelete() {}
    override fun addExpression(operations: Any) {}
}