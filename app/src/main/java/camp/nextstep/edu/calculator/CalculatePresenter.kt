package camp.nextstep.edu.calculator

import com.example.domain.Calculator
import com.example.domain.OperationTerm
import com.example.domain.Statement

class CalculatePresenter(private val view: CalculateContract.View) : CalculateContract.Presenter {

    private val statement: Statement = Statement()
    private val calculator: Calculator = Calculator()

    override fun addTerm(term: OperationTerm) {
        statement.addTerm(term)
        view.showStatement(statement.termsToString())
    }

    override fun removeTerm() {
        statement.removeTerm()
        view.showStatement(statement.termsToString())
    }

    override fun calculate() {
        try {
            val result = calculator.calculate(statement.termsToString())
            view.showStatement(result.toString())
        } catch (e: Throwable) {
            view.showCalculateError(e.message.toString())
        }
    }
}
