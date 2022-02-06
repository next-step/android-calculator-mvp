package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.addToExpression(0) }
        binding.button1.setOnClickListener { presenter.addToExpression(1) }
        binding.button2.setOnClickListener { presenter.addToExpression(2) }
        binding.button3.setOnClickListener { presenter.addToExpression(3) }
        binding.button4.setOnClickListener { presenter.addToExpression(4) }
        binding.button5.setOnClickListener { presenter.addToExpression(5) }
        binding.button6.setOnClickListener { presenter.addToExpression(6) }
        binding.button7.setOnClickListener { presenter.addToExpression(7) }
        binding.button8.setOnClickListener { presenter.addToExpression(8) }
        binding.button9.setOnClickListener { presenter.addToExpression(9) }
        binding.buttonPlus.setOnClickListener {
            expression += Operator.Plus
            binding.textView.text = expression.toString()
        }
        binding.buttonMinus.setOnClickListener {
            expression += Operator.Minus
            binding.textView.text = expression.toString()
        }
        binding.buttonMultiply.setOnClickListener {
            expression += Operator.Multiply
            binding.textView.text = expression.toString()
        }
        binding.buttonDivide.setOnClickListener {
            expression += Operator.Divide
            binding.textView.text = expression.toString()
        }
        binding.buttonDelete.setOnClickListener {
            expression = expression.removeLast()
            binding.textView.text = expression.toString()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.evaluate()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showToast() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
