package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.CAST_INT_EXCEPTION
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val calculator = Calculator()
	private var expression = Expression()

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
		expression = expression.insertOperand(operand)
		showExpression()
	}

	private fun setOperatorListener() {
		binding.buttonPlus.setOnClickListener { insertOperator(Operator.PLUS) }
		binding.buttonMinus.setOnClickListener { insertOperator(Operator.MINUS) }
		binding.buttonMultiply.setOnClickListener { insertOperator(Operator.MUL) }
		binding.buttonDivide.setOnClickListener { insertOperator(Operator.DIV) }
	}

	private fun insertOperator(operator: Operator) {
		expression = expression.insertOperator(operator)
		showExpression()
	}

	private fun setDeleteListener() {
		binding.buttonDelete.setOnClickListener {
			expression = expression.delete()
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
		runCatching {
			calculator.calculate(expression.getCompleteExpression())
		}.onSuccess {
			showResult(it)
		}.onFailure {
			showToastIfNeed(it.message)
		}
	}

	private fun showResult(result: Int) {
		binding.textView.text = result.toString()
		initExpression()
	}

	private fun initExpression() {
		expression = Expression()
	}

	private fun showToastIfNeed(errorMessage: String?) {
		when (errorMessage) {
			Expression.EXP_NOT_COMPLETE -> Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_LONG).show()
			Expression.EXP_IS_BLANK -> Toast.makeText(this, "계산식이 입력되지 않았습니다.", Toast.LENGTH_LONG).show()
			Operator.INVALID_OPERATOR -> Toast.makeText(this, "유효하지 않은 연산자가 입력되었습니다.", Toast.LENGTH_LONG).show()
			CAST_INT_EXCEPTION -> Toast.makeText(this, "숫자가 아닌 피연산자가 입력되었습니다.", Toast.LENGTH_LONG).show()
			Calculator.DIVIDE_BY_ZERO -> Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show()
			else -> Unit
		}
	}
}
