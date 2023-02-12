package camp.nextstep.edu.calculator

import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

interface MainContract {
    interface View {
        val presenter: Presenter
        fun showResult(result: String)
        fun showExpression(expression: Expression)
        fun showErrorMessage(errorMessage: String?)
    }

    interface Presenter {
        fun callEquals()
        fun callDelete()
        fun addOperator(operator: Operator)
        fun addOperation(operation: Int)
        fun initExpression(expression: Expression)
    }
}