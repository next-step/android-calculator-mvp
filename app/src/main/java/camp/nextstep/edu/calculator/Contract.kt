package camp.nextstep.edu.calculator

interface BaseView<T> {
    var presenter: T
}

interface Contract {
    interface View: BaseView<Presenter> {
        // 수식을 보여줌
        fun showFormula(formula: String)

        // 수식의 결과를 보여준다.
        fun showResult(result: String)

        // 토스트 메시지를 보여준다.
        fun showToast(msg: String)
    }

    interface Presenter {
        // 수식에 숫자 추가
        fun addNumber(char: Char)

        // 수식에 연산자 추가
        fun addOperator(char: Char)

        // 수식의 마지막 문자 제거
        fun deleteLastStr()

        fun calculate()
    }
}