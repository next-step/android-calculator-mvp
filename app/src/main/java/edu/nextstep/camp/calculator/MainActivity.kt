package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            presenter.clickOperand(0)
        }
        binding.button1.setOnClickListener {
            presenter.clickOperand(1)
        }
        binding.button2.setOnClickListener {
            presenter.clickOperand(2)
        }
        binding.button3.setOnClickListener {
            presenter.clickOperand(3)
        }
        binding.button4.setOnClickListener {
            presenter.clickOperand(4)
        }
        binding.button5.setOnClickListener {
            presenter.clickOperand(5)
        }
        binding.button6.setOnClickListener {
            presenter.clickOperand(6)
        }
        binding.button7.setOnClickListener {
            presenter.clickOperand(7)
        }
        binding.button8.setOnClickListener {
            presenter.clickOperand(8)
        }
        binding.button9.setOnClickListener {
            presenter.clickOperand(9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.clickOperator(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.clickOperator(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.clickOperator(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.clickOperator(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.clickDelete()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.clickEqual()
        }
    }

    override fun showExpression(result: String) {
        binding.textView.text = result
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
