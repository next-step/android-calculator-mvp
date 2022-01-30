package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)
        initLayout()
    }

    private fun initLayout() {
        binding.button0.setOnClickListener { presenter.addOperand(binding.button0.text.toString()) }
        binding.button1.setOnClickListener { presenter.addOperand(binding.button1.text.toString()) }
        binding.button2.setOnClickListener { presenter.addOperand(binding.button2.text.toString()) }
        binding.button3.setOnClickListener { presenter.addOperand(binding.button3.text.toString()) }
        binding.button4.setOnClickListener { presenter.addOperand(binding.button4.text.toString()) }
        binding.button5.setOnClickListener { presenter.addOperand(binding.button5.text.toString()) }
        binding.button6.setOnClickListener { presenter.addOperand(binding.button6.text.toString()) }
        binding.button7.setOnClickListener { presenter.addOperand(binding.button7.text.toString()) }
        binding.button8.setOnClickListener { presenter.addOperand(binding.button8.text.toString()) }
        binding.button9.setOnClickListener { presenter.addOperand(binding.button9.text.toString()) }

        binding.buttonDivide.setOnClickListener { presenter.addOperator(binding.buttonDivide.text.toString()) }
        binding.buttonMultiply.setOnClickListener { presenter.addOperator(binding.buttonMultiply.text.toString()) }
        binding.buttonMinus.setOnClickListener { presenter.addOperator(binding.buttonMinus.text.toString()) }
        binding.buttonPlus.setOnClickListener { presenter.addOperator(binding.buttonPlus.text.toString()) }
        binding.buttonDelete.setOnClickListener { presenter.removeLast() }

        binding.buttonEquals.setOnClickListener { presenter.getResult() }
    }

    override fun refreshExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showResult(result: String) {
        binding.textView.text = result
    }

    override fun showToast(result: String) {
        Toast.makeText(this@MainActivity, "완성되지 않은 수식입니다", Toast.LENGTH_SHORT).show()
    }
}
