package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val operandButtonArray = arrayOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9,
        )

        bindOnClickOperand(buttons = operandButtonArray)

        val operatorButtonArray = arrayOf(
            binding.buttonPlus, binding.buttonMinus, binding.buttonMultiply, binding.buttonDivide,
        )

        bindOnClickOperator(buttons = operatorButtonArray)

        binding.buttonDelete.setOnClickListener {
            presenter.dropLast()
        }

        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    private fun bindOnClickOperand(vararg buttons: Button) {
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                presenter.addOperand(button.text.toString().toInt())
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindOnClickOperator(vararg buttons: Button) {
        buttons.forEach { button ->
            button.setOnClickListener {
                presenter.addOperator(Operator.of(button.text.toString()))
            }
        }
    }

    override fun showCurrentExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showCalculateValue(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showExpressionErrorToast() {
        Toast.makeText(applicationContext, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

}