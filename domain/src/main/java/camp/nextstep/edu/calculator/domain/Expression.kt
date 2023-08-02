package camp.nextstep.edu.calculator.domain

class Expression(
    private val onValueChanged: (String) -> Unit,
) {
    var value: String = ""
        private set

    fun setOperand(operand: String) {
        value += operand
        onValueChanged(value)
    }

    fun setOperator(operator: String) {
        if (value.last().isDigit()) {
            value += " $operator "
            onValueChanged(value)
            return
        }

        if (ArithmeticOperator.isArithmeticOperator(value.trimEnd().last().toString())) {
            value = value.dropLast(3) + " $operator "
            onValueChanged(value)
        }
    }

    fun setEquals(result: String) {
        value = result
        onValueChanged(value)
    }

    fun setDelete() {
        if (value.last().isDigit()) {
            value = value.dropLast(1)
            onValueChanged(value)
            return
        }

        if (ArithmeticOperator.isArithmeticOperator(value.trimEnd().last().toString())) {
            value = value.dropLast(3)
            onValueChanged(value)
        }
    }
}
