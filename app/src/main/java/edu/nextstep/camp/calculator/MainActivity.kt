package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity() : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override val presenter: MainContract.Presenter = MainPresenter(this)
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            presenter.addToExpression(binding.button0.text.toString().toInt())
        }
        binding.button1.setOnClickListener {
            presenter.addToExpression(binding.button1.text.toString().toInt())
        }
        binding.button2.setOnClickListener {
            presenter.addToExpression(binding.button2.text.toString().toInt())
        }
        binding.button3.setOnClickListener {
            presenter.addToExpression(binding.button3.text.toString().toInt())
        }
        binding.button4.setOnClickListener {
            presenter.addToExpression(binding.button4.text.toString().toInt())
        }
        binding.button5.setOnClickListener {
            presenter.addToExpression(binding.button5.text.toString().toInt())
        }
        binding.button6.setOnClickListener {
            presenter.addToExpression(binding.button6.text.toString().toInt())
        }
        binding.button7.setOnClickListener {
            presenter.addToExpression(binding.button7.text.toString().toInt())
        }
        binding.button8.setOnClickListener {
            presenter.addToExpression(binding.button8.text.toString().toInt())
        }
        binding.button9.setOnClickListener {
            presenter.addToExpression(binding.button9.text.toString().toInt())
        }
        binding.buttonPlus.setOnClickListener {
            presenter.addToExpression(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.addToExpression(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.addToExpression(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.addToExpression(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastInExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.evaluateExpression()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
