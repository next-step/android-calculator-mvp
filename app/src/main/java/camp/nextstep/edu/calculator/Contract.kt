package camp.nextstep.edu.calculator

interface Contract {
    interface View {
        val presenter: Presenter

        fun display(text: String)
    }

    interface Presenter {
        fun addExpressionText(text: String)
        fun removeExpressionItem()
        fun calculate()
    }
}
