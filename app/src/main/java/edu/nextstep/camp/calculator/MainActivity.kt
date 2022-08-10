package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operator


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.appendOperand(0) }
        binding.button1.setOnClickListener { presenter.appendOperand(1) }
        binding.button2.setOnClickListener { presenter.appendOperand(2) }
        binding.button3.setOnClickListener { presenter.appendOperand(3) }
        binding.button4.setOnClickListener { presenter.appendOperand(4) }
        binding.button5.setOnClickListener { presenter.appendOperand(5) }
        binding.button6.setOnClickListener { presenter.appendOperand(6) }
        binding.button7.setOnClickListener { presenter.appendOperand(7) }
        binding.button8.setOnClickListener { presenter.appendOperand(8) }
        binding.button9.setOnClickListener { presenter.appendOperand(9) }

        binding.buttonPlus.setOnClickListener { presenter.appendOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.appendOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.appendOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.appendOperator(Operator.Divide) }

        binding.buttonDelete.setOnClickListener { presenter.deleteLast() }
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    override fun showExpression(text: String) {
        binding.textView.text = text
    }

    override fun showErrorToast() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
