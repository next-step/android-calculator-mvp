package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)

        binding.button0.setOnClickListener {
            mainPresenter.input(0)
        }
        binding.button1.setOnClickListener {
            mainPresenter.input(1)
        }
        binding.button2.setOnClickListener {
            mainPresenter.input(2)
        }
        binding.button3.setOnClickListener {
            mainPresenter.input(3)
        }
        binding.button4.setOnClickListener {
            mainPresenter.input(4)
        }
        binding.button5.setOnClickListener {
            mainPresenter.input(5)
        }
        binding.button6.setOnClickListener {
            mainPresenter.input(6)
        }
        binding.button7.setOnClickListener {
            mainPresenter.input(7)
        }
        binding.button8.setOnClickListener {
            mainPresenter.input(8)
        }
        binding.button9.setOnClickListener {
            mainPresenter.input(9)
        }
        binding.buttonPlus.setOnClickListener {
            mainPresenter.input(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            mainPresenter.input(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            mainPresenter.input(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            mainPresenter.input(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            mainPresenter.removeLast()
        }
        binding.buttonEquals.setOnClickListener {
            val result = mainPresenter.calculate()
            if (result == null) {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mainPresenter.initExpression(result)
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }
}
