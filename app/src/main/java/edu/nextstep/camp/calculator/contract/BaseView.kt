package edu.nextstep.camp.calculator.contract

import edu.nextstep.camp.calculator.domain.ExpressionTokenProcessor
import edu.nextstep.camp.calculator.domain.model.ExpressionToken

interface BaseView<T> {
    var presenter: T
}

interface MainContract {
    interface View : BaseView<Presenter> { 
        fun displayExpression(expression: String)
    }
    interface Presenter {
        fun addExpressionToken(expressionToken: ExpressionToken)
    }

    class MainPresenter(private val view: View) : Presenter {
        private val expressionTokenProcessor = ExpressionTokenProcessor()

        override fun addExpressionToken(expressionToken: ExpressionToken) {
            view.displayExpression(expressionTokenProcessor.addExpressionToken(expressionToken))
        }
    }
}
