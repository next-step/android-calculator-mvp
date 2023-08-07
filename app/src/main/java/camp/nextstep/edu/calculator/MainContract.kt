package camp.nextstep.edu.calculator

interface MainContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: String)
        fun showError(message: String?)
    }

    interface Presenter {
        fun addOperand(operand: String)
        fun addOpcode(opcode: String)
        fun deleteFormula()
        fun calculate()
    }
}
