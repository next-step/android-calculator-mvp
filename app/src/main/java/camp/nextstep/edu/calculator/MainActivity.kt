package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val expression = Expression()
	private val calculator = Calculator()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setOperandListener()
		setOperatorListener()
		setDeleteListener()
		setEqualsListener()
	}

	private fun setOperandListener() {
		binding.button0.setOnClickListener { insertOperand("0") }
		binding.button1.setOnClickListener { insertOperand("1") }
		binding.button2.setOnClickListener { insertOperand("2") }
		binding.button3.setOnClickListener { insertOperand("3") }
		binding.button4.setOnClickListener { insertOperand("4") }
		binding.button5.setOnClickListener { insertOperand("5") }
		binding.button6.setOnClickListener { insertOperand("6") }
		binding.button7.setOnClickListener { insertOperand("7") }
		binding.button8.setOnClickListener { insertOperand("8") }
		binding.button9.setOnClickListener { insertOperand("9") }
	}

	private fun insertOperand(operand: String) {
		expression.insertOperand(operand)
		showExpression()
	}

	private fun setOperatorListener() {
		binding.buttonPlus.setOnClickListener { insertOperator(Operator.PLUS) }
		binding.buttonMinus.setOnClickListener { insertOperator(Operator.MINUS) }
		binding.buttonMultiply.setOnClickListener { insertOperator(Operator.MUL) }
		binding.buttonDivide.setOnClickListener { insertOperator(Operator.DIV) }
	}

	private fun insertOperator(operator: Operator) {
		expression.insertOperator(operator)
		showExpression()
	}

	private fun setDeleteListener() {
		binding.buttonDelete.setOnClickListener {
			expression.delete()
			showExpression()
		}
	}

	private fun showExpression() {
		binding.textView.text = expression.toString()
	}

	private fun setEqualsListener() {
		binding.buttonEquals.setOnClickListener {
			showResultOrThrow()
		}
	}

	private fun showResultOrThrow() {
		try {
			val result = calculator.calculate(expression.getOrThrow())
			showResult(result)
		} catch (e: IllegalStateException) {
			showToastIfNeed(e)
		} catch (e: IllegalArgumentException) {
			Unit
		} catch (e: ArithmeticException) {
			Unit
		}
	}

	private fun showResult(result: Int) {
		binding.textView.text = result.toString()
		expression.clear()
	}

	private fun showToastIfNeed(e: IllegalStateException) {
		if (e.message == Expression.EXP_NOT_COMPLETE) {
			Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_LONG).show()
		}
	}
}
