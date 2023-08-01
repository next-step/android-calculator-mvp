package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var expression: Expression

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initExpression()

		setOperandListener()
		setOperatorListener()
	}

	private fun initExpression() {
		expression = Expression()
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

	private fun showExpression() {
		binding.textView.text = expression.toString()
	}
}
