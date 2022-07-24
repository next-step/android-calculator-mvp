package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtons()
    }

    private fun setButtons() {
        setOperandButtons()
        setOperatorButtons()
        setFunctionalButtons()
    }

    private fun setOperandButtons() {
        with(binding) {
            button0 setExpressionOperandAddClickListener 0
            button1 setExpressionOperandAddClickListener 1
            button2 setExpressionOperandAddClickListener 2
            button3 setExpressionOperandAddClickListener 3
            button4 setExpressionOperandAddClickListener 4
            button5 setExpressionOperandAddClickListener 5
            button6 setExpressionOperandAddClickListener 6
            button7 setExpressionOperandAddClickListener 7
            button8 setExpressionOperandAddClickListener 8
            button9 setExpressionOperandAddClickListener 9
        }
    }

    private fun setOperatorButtons() {
        with(binding) {
            buttonPlus setExpressionOperatorAddClickListener Operator.Plus
            buttonMinus setExpressionOperatorAddClickListener Operator.Minus
            buttonMultiply setExpressionOperatorAddClickListener Operator.Multiply
            buttonDivide setExpressionOperatorAddClickListener Operator.Divide
        }
    }

    private fun setFunctionalButtons() {
        binding.buttonDelete.setOnClickListener {
            expression = expression.removeLast()
            binding.textView.text = expression.toString()
        }
        binding.buttonEquals.setOnClickListener {
            val result = calculator.calculate(expression.toString())
            if (result == null) {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            expression = Expression.EMPTY + result
            binding.textView.text = result.toString()
        }
    }

    private infix fun Button.setExpressionOperandAddClickListener(value: Int) =
        setOnClickListener {
            expression += value
            binding.textView.text = expression.toString()
        }

    private infix fun Button.setExpressionOperatorAddClickListener(operator: Operator) =
        setOnClickListener {
            expression += operator
            binding.textView.text = expression.toString()
        }
}
