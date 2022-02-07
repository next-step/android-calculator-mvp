package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override val presenter: MainContract.Presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            presenter.addToExpression(0)
        }
        binding.button1.setOnClickListener {
            presenter.addToExpression(1)
        }
        binding.button2.setOnClickListener {
            presenter.addToExpression(2)
        }
        binding.button3.setOnClickListener {
            presenter.addToExpression(3)
        }
        binding.button4.setOnClickListener {
            presenter.addToExpression(4)
        }
        binding.button5.setOnClickListener {
            presenter.addToExpression(5)
        }
        binding.button6.setOnClickListener {
            presenter.addToExpression(6)
        }
        binding.button7.setOnClickListener {
            presenter.addToExpression(7)
        }
        binding.button8.setOnClickListener {
            presenter.addToExpression(8)
        }
        binding.button9.setOnClickListener {
            presenter.addToExpression(9)
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
            presenter.removeLastToExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculateToExpression()
        }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showToast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }
}
