package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator
    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculator = Calculator()
        expression = Expression { value -> binding.textView.text = value }

        initOperandButtons()
        initOperatorButtons()

        with(binding) {
            buttonEquals.setOnClickListener { setEqualsClickListener() }
            buttonDelete.setOnClickListener { setDeleteClickListener() }
        }
    }

    private fun initOperandButtons() {
        with(binding) {
            button0.setOnClickListener { expression.setOperand("0") }
            button1.setOnClickListener { expression.setOperand("1") }
            button2.setOnClickListener { expression.setOperand("2") }
            button3.setOnClickListener { expression.setOperand("3") }
            button4.setOnClickListener { expression.setOperand("4") }
            button5.setOnClickListener { expression.setOperand("5") }
            button6.setOnClickListener { expression.setOperand("6") }
            button7.setOnClickListener { expression.setOperand("7") }
            button8.setOnClickListener { expression.setOperand("8") }
            button9.setOnClickListener { expression.setOperand("9") }
        }
    }

    private fun initOperatorButtons() {
        with(binding) {
            buttonPlus.setOnClickListener { setOperatorClickListener(ArithmeticOperator.PLUS) }
            buttonMinus.setOnClickListener { setOperatorClickListener(ArithmeticOperator.MINUS) }
            buttonMultiply.setOnClickListener { setOperatorClickListener(ArithmeticOperator.MULTIPLY) }
            buttonDivide.setOnClickListener { setOperatorClickListener(ArithmeticOperator.DIVIDE) }
        }
    }

    private fun setOperatorClickListener(operator: ArithmeticOperator) {
        expression.setOperator(operator.value)
    }

    private fun setEqualsClickListener() {
        kotlin.runCatching {
            calculator.calculate(ArithmeticExpression(expression.value)).toString()
        }.onSuccess { result ->
            expression.setEquals(result)
        }.onFailure { exception ->
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDeleteClickListener() {
        expression.setDelete()
    }
}
