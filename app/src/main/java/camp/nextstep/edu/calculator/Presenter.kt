package camp.nextstep.edu.calculator

import com.example.calculatorlib.Calculator
import com.example.calculatorlib.Formula

class Presenter(private val view: Contract.View): Contract.Presenter {

    private val formula = Formula()
    private val calculate = Calculator()

    // 숫자 추가
    override fun addNumber(char: Char) {
        view.showFormula(formula.addNumber(char))
    }

    // 연산자 추가
    // 직전 값이 연산자일 경우 교체한다.
    override fun addOperator(char: Char) {
        view.showFormula(formula.addOperator(char))
    }

    // 최근 항목 삭제
    override fun deleteLastStr() {
        view.showFormula(formula.deleteLastStr())
    }

    // 수식 계산
    override fun calculate() {
        if (isLastStrNum()) {
            val formula = formula.getFormula()
            val result = calculate.evaluate(formula)
            view.showResult(result.toString())
        } else {
            view.showToast("완성되지 않은 수식입니다")
        }
    }

    // 직전 값이 숫자인가
    // return True: 숫자, False: 연산자
    // 직전 값이 없다면?
    private fun isLastStrNum(): Boolean {
        return formula.isLastStrNum()
    }
}