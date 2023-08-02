package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Expression

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private val expression = Expression()
    private val calculator = Calculator

    override fun onButtonClicked(text: String) {
        expression.setExpressions(text)
        view.showResult(expression.expressions)
    }

    override fun onDeleteClicked() {
        expression.removeLatestExpression()
        view.showResult(expression.expressions)
    }

    override fun onEqualsClicked() {
        runCatching {
            calculator.evaluate(expression.expressions)
        }.onSuccess {
            view.showResult(it.toString())
        }.onFailure {
            view.showErrorToastMessage()
        }
    }

    override fun onMemoryClicked() {
        expression.resetMemory()
        view.showResult(expression.expressions)
    }
}