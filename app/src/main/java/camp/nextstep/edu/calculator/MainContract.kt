package camp.nextstep.edu.calculator

interface MainContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: String)
        fun showToast(message: String)
    }

    interface Presenter {
        fun addInputValue(inputValue: String)
        fun deleteLast()
        fun evaluate()
    }
}