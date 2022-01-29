package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewsClickListener()
    }

    private fun setViewsClickListener() {
        binding.run {
            setNumberButtonsClickListener(
                button0, button1, button2, button3, button4, button5, button6, button7, button8,
                button9,
            )
            setOperatorButtonClickListener(buttonPlus, buttonMinus, buttonMultiply, buttonDivide)
            buttonDelete.setOnClickListener { setDeleteButtonClickListener() }
            buttonEquals.setOnClickListener { setEqualsButtonClickListener() }
        }
    }

    private fun setNumberButtonsClickListener(vararg buttons: Button) {
        buttons.forEach {
            expression += it.text.toString().toInt()
            binding.textView.text = expression.toString()
        }
    }

    private fun setOperatorButtonClickListener(vararg buttons: Button) {
        buttons.forEach {
            expression += when {
                (it.text.toString() == getString(R.string.calculator_plus)) -> Operator.Plus
                (it.text.toString() == getString(R.string.calculator_minus)) -> Operator.Minus
                (it.text.toString() == getString(R.string.calculator_multiply)) -> Operator.Multiply
                (it.text.toString() == getString(R.string.calculator_divide)) -> Operator.Divide
                else -> throw IllegalArgumentException("${it.text} is not an operator")
            }

            binding.textView.text = expression.toString()
        }
    }

    private fun setDeleteButtonClickListener() {
        expression = expression.removeLast()
        binding.textView.text = expression.toString()
    }

    private fun setEqualsButtonClickListener() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            Toast.makeText(
                this@MainActivity,
                R.string.incomplete_expression,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        expression = Expression.EMPTY + result
        binding.textView.text = result.toString()
    }
}
