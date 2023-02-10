package camp.nextstep.edu.calculator

import com.example.domain.Expression
import com.example.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)

        fun showToastMessage(expression: Expression)

        fun showResult(result: String)
    }

    interface Presenter {
        fun appendOperandInExpression(value: Int)

        fun appendOperatorInExpression(value: Operator)

        fun returnResult()

        fun removeLastValueInExpression()
    }
}