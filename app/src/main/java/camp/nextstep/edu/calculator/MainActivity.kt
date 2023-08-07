package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.CAST_INT_EXCEPTION
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
	private lateinit var binding: ActivityMainBinding
	override lateinit var presenter: MainContract.Presenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initMainPresenter()

		setOperandListener()
		setOperatorListener()
		setDeleteListener()
		setEqualsListener()
	}

	private fun initMainPresenter() {
		presenter = MainPresenterImpl(this)
	}

	private fun setOperandListener() {
		binding.button0.setOnClickListener { presenter.plus("0") }
		binding.button1.setOnClickListener { presenter.plus("1") }
		binding.button2.setOnClickListener { presenter.plus("2") }
		binding.button3.setOnClickListener { presenter.plus("3") }
		binding.button4.setOnClickListener { presenter.plus("4") }
		binding.button5.setOnClickListener { presenter.plus("5") }
		binding.button6.setOnClickListener { presenter.plus("6") }
		binding.button7.setOnClickListener { presenter.plus("7") }
		binding.button8.setOnClickListener { presenter.plus("8") }
		binding.button9.setOnClickListener { presenter.plus("9") }
	}

	private fun setOperatorListener() {
		binding.buttonPlus.setOnClickListener { presenter.plus(Operator.PLUS) }
		binding.buttonMinus.setOnClickListener { presenter.plus(Operator.MINUS) }
		binding.buttonMultiply.setOnClickListener { presenter.plus(Operator.MUL) }
		binding.buttonDivide.setOnClickListener { presenter.plus(Operator.DIV) }
	}

	private fun setDeleteListener() {
		binding.buttonDelete.setOnClickListener { presenter.delete() }
	}

	private fun setEqualsListener() {
		binding.buttonEquals.setOnClickListener { presenter.showResultOrThrow() }
	}

	override fun showExpression(expression: Expression) {
		binding.textView.text = expression.toString()
	}

	override fun showResult(result: String) {
		binding.textView.text = result
	}

	override fun showToast(message: String?) {
		when (message) {
			Expression.EXP_NOT_COMPLETE -> Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_LONG).show()
			Expression.EXP_IS_BLANK -> Toast.makeText(this, "계산식이 입력되지 않았습니다.", Toast.LENGTH_LONG).show()
			Operator.INVALID_OPERATOR -> Toast.makeText(this, "유효하지 않은 연산자가 입력되었습니다.", Toast.LENGTH_LONG).show()
			CAST_INT_EXCEPTION -> Toast.makeText(this, "숫자가 아닌 피연산자가 입력되었습니다.", Toast.LENGTH_LONG).show()
			Calculator.DIVIDE_BY_ZERO -> Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show()
			else -> Unit
		}
	}
}
