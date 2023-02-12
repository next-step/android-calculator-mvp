package camp.nextstep.edu.calculator

import com.example.domain.OperationTerm

interface CalculateContract {
    interface View {
        var presenter: Presenter

        fun showStatement(statement: String)

        fun showCalculateError(error: String)
    }

    interface Presenter {
        fun addTerm(term: OperationTerm)

        fun removeTerm()

        fun calculate()
    }
}