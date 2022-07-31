package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.domain.CalculationHistoryManager
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter: MainContract.Presenter = MainPresenter(this, Calculator(), CalculationHistoryManager())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { presenter.addNumberToExpression(0) }
        binding.button1.setOnClickListener { presenter.addNumberToExpression(1) }
        binding.button2.setOnClickListener { presenter.addNumberToExpression(2) }
        binding.button3.setOnClickListener { presenter.addNumberToExpression(3) }
        binding.button4.setOnClickListener { presenter.addNumberToExpression(4) }
        binding.button5.setOnClickListener { presenter.addNumberToExpression(5) }
        binding.button6.setOnClickListener { presenter.addNumberToExpression(6) }
        binding.button7.setOnClickListener { presenter.addNumberToExpression(7) }
        binding.button8.setOnClickListener { presenter.addNumberToExpression(8) }
        binding.button9.setOnClickListener { presenter.addNumberToExpression(9) }

        binding.buttonPlus.setOnClickListener { presenter.addOperatorToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addOperatorToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addOperatorToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addOperatorToExpression(Operator.Divide) }

        binding.buttonDelete.setOnClickListener { presenter.removeLastToken() }
        binding.buttonEquals.setOnClickListener { presenter.calculateCurrentExpression() }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }

    override fun showErrorMessage(exception: Exception) {
        if (exception is IncompleteExpressionException) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }
}