package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val stringExpressionState: StringExpressionState
        get() = StringExpressionState.of(binding.textView.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()
        initOperandButtons()
        initOperatorButtons()
        initDeleteButton()
        initEqualsButton()
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initOperandButtons() {
        (0..9).forEach { number ->
            val viewId = resources.getIdentifier("$BUTTON_VIEW_PREFIX$number", "id", packageName)
            val button = findViewById(viewId) as? Button ?: return@forEach
            setOperandButtonListener(button, Operand(number))
        }
    }

    private fun setOperandButtonListener(button: Button, operand: Operand) {
        button.setOnClickListener {
            binding.textView.text = stringExpressionState.addElement(operand).toString()
        }
    }

    private fun initOperatorButtons() {
        listOf(
            binding.buttonPlus to Operator.PLUS,
            binding.buttonMinus to Operator.MINUS,
            binding.buttonMultiply to Operator.MULTIPLY,
            binding.buttonDivide to Operator.DIVIDE,
        ).forEach { (button, operator) ->
            setOperatorButtonListener(
                button = button,
                operator = operator
            )
        }
    }

    private fun setOperatorButtonListener(button: Button, operator: Operator) {
        button.setOnClickListener {
            binding.textView.text = stringExpressionState.addElement(operator).toString()
        }
    }

    private fun initDeleteButton() {
        binding.buttonDelete.setOnClickListener {
            binding.textView.text = stringExpressionState.removeElement().toString()
        }
    }

    private fun initEqualsButton() {
        binding.buttonEquals.setOnClickListener {
            runCatching {
                StringCalculator.calculate(stringExpressionState)
            }
                .onSuccess {
                    binding.textView.text = it.value.toString()
                }
                .onFailure {
                    Toast
                        .makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }

    companion object {
        private const val BUTTON_VIEW_PREFIX = "button"
    }
}
