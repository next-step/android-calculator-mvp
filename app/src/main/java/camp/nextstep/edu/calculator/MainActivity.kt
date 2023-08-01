package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator

    private var currentInput: String = ""
        set(value) {
            field = value
            binding.textView.text = currentInput
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculator = Calculator()

        initNumericKeypad()
        initOperatorButtons()

        with(binding) {
            buttonEquals.setOnClickListener { setEqualsClickListener() }
            buttonDelete.setOnClickListener { setDeleteClickListener() }
        }
    }

    private fun initNumericKeypad() {
        with(binding) {
            button0.setOnClickListener { currentInput += "0" }
            button1.setOnClickListener { currentInput += "1" }
            button2.setOnClickListener { currentInput += "2" }
            button3.setOnClickListener { currentInput += "3" }
            button4.setOnClickListener { currentInput += "4" }
            button5.setOnClickListener { currentInput += "5" }
            button6.setOnClickListener { currentInput += "6" }
            button7.setOnClickListener { currentInput += "7" }
            button8.setOnClickListener { currentInput += "8" }
            button9.setOnClickListener { currentInput += "9" }
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
        if (currentInput.last().isDigit()) {
            currentInput += " ${operator.value} "
            return
        }

        if (ArithmeticOperator.isArithmeticOperator(currentInput.trimEnd().last().toString())) {
            currentInput =
                currentInput.dropLast(3) + " ${operator.value} "
        }
    }

    private fun setEqualsClickListener() {
        kotlin.runCatching {
            calculator.calculate(ArithmeticExpression(currentInput)).toString()
        }.onSuccess { result ->
            currentInput = result
        }.onFailure { exception ->
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDeleteClickListener() {
        if (currentInput.last().isDigit()) {
            currentInput = currentInput.dropLast(1)
            return
        }

        if (ArithmeticOperator.isArithmeticOperator(currentInput.trimEnd().last().toString())) {
            currentInput = currentInput.dropLast(3)
        }
    }
}
