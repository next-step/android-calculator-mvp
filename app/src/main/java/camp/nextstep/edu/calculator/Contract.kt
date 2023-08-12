package camp.nextstep.edu.calculator

interface Contract {
    interface View {
        val presenter: Presenter

        fun display(text: String)
        fun displayExpressionError()
    }

    interface Presenter {
        fun addExpressionText(text: String)
        fun removeExpressionItem()
        fun calculate()
    }
}
