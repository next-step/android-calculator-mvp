package camp.nextstep.edu.calculator

import com.nextstep.edu.calculator.domain.Expression

interface MainContract {
    interface View {
        val presenter: Presenter
        fun showResult(result: String)
        fun showExpression(expression: Expression)
    }

    interface Presenter {
        fun callEquals()
        fun callDelete()
        fun addExpression(operations: Any)
    }
}