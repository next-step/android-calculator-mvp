package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.Evaluator
import camp.nextstep.edu.domain.ExpressionHandler

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val evaluator = Evaluator()
    private val expressionHandler = ExpressionHandler()

    override fun addInputValue(inputValue: String) {
        expressionHandler.addInputValue(inputValue)
        view.showExpression(expressionHandler.expression)
    }

    override fun deleteLast() {
        expressionHandler.deleteLast()
        view.showExpression(expressionHandler.expression)
    }

    override fun evaluate() {
        runCatching {
            evaluator.evaluate(expressionHandler.expression)
        }.onSuccess {
            view.showExpression(it.toString())
        }.onFailure {
            view.showToast(it.message ?: "")
        }
    }
}