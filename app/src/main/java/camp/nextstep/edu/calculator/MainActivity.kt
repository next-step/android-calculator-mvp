package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.addOperand("0") }
        binding.button1.setOnClickListener { presenter.addOperand("1") }
        binding.button2.setOnClickListener { presenter.addOperand("2") }
        binding.button3.setOnClickListener { presenter.addOperand("3") }
        binding.button4.setOnClickListener { presenter.addOperand("4") }
        binding.button5.setOnClickListener { presenter.addOperand("5") }
        binding.button6.setOnClickListener { presenter.addOperand("6") }
        binding.button7.setOnClickListener { presenter.addOperand("7") }
        binding.button8.setOnClickListener { presenter.addOperand("8") }
        binding.button9.setOnClickListener { presenter.addOperand("9") }

        binding.buttonPlus.setOnClickListener { presenter.addOpcode("+") }
        binding.buttonMinus.setOnClickListener { presenter.addOpcode("-") }
        binding.buttonMultiply.setOnClickListener { presenter.addOpcode("*") }
        binding.buttonDivide.setOnClickListener { presenter.addOpcode("/") }

        binding.buttonDelete.setOnClickListener { presenter.deleteFormula() }
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showError(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
