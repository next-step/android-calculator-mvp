package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var presenter: CalculatorContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CalculatorPresenter(this)

        initViews()
    }

    private fun initViews() {
        binding.button0.setOnClickListener { presenter.appendExpression("0") }
        binding.button1.setOnClickListener { presenter.appendExpression("1") }
        binding.button2.setOnClickListener { presenter.appendExpression("2") }
        binding.button3.setOnClickListener { presenter.appendExpression("3") }
        binding.button4.setOnClickListener { presenter.appendExpression("4") }
        binding.button5.setOnClickListener { presenter.appendExpression("5") }
        binding.button6.setOnClickListener { presenter.appendExpression("6") }
        binding.button7.setOnClickListener { presenter.appendExpression("7") }
        binding.button8.setOnClickListener { presenter.appendExpression("8") }
        binding.button9.setOnClickListener { presenter.appendExpression("9") }

        binding.buttonPlus.setOnClickListener { presenter.appendExpression(plus) }
        binding.buttonMinus.setOnClickListener { presenter.appendExpression(minus) }
        binding.buttonMultiply.setOnClickListener { presenter.appendExpression(multiply) }
        binding.buttonDivide.setOnClickListener { presenter.appendExpression(divide) }

        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }

        binding.buttonDelete.setOnClickListener {
            presenter.removeExpression()
        }
    }

    override fun inCompleteExpressionNotice() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showExpressions(expression: String) {
        binding.textView.text = expression
    }

    override fun showCalculationResult(expression: String, result: Double): String {
        val expressionResult = String.format(resources.getString(R.string.calculation_result), expression, result)
        binding.textView.text = expressionResult
        return expressionResult
    }
}
