package camp.nextstep.edu.calculator

import com.example.domain.Expression
import com.example.domain.Operator

interface MainContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: Expression)

        fun showToastMessage(expression: Expression)
    }

    interface Presenter {
        fun appendOperandInExpression(value: Int)
        fun appendOperatorInExpression(value: Operator)

        fun returnResult() : String
        fun removeLastValueInExpression()
    }
}