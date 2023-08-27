package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.CalculationOperator

class MainActivity : AppCompatActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainPresenter()

        setOnNumberClickListener()
        setOnOperatorClickListener()
        setOnDeleteClickListener()
        setOnCalculateClickListener()
    }

    private fun initMainPresenter() {
        presenter = MainPresenter(this)
    }

    private fun setOnNumberClickListener() {
        with(binding) {
            button0.setOnClickListener { presenter.addInputValue("0") }
            button1.setOnClickListener { presenter.addInputValue("1") }
            button2.setOnClickListener { presenter.addInputValue("2") }
            button3.setOnClickListener { presenter.addInputValue("3") }
            button4.setOnClickListener { presenter.addInputValue("4") }
            button5.setOnClickListener { presenter.addInputValue("5") }
            button6.setOnClickListener { presenter.addInputValue("6") }
            button7.setOnClickListener { presenter.addInputValue("7") }
            button8.setOnClickListener { presenter.addInputValue("8") }
            button9.setOnClickListener { presenter.addInputValue("9") }
        }
    }

    private fun setOnOperatorClickListener() {
        with(binding) {
            buttonPlus.setOnClickListener { presenter.addInputValue(" ${CalculationOperator.PLUS.symbol} ") }
            buttonMinus.setOnClickListener { presenter.addInputValue(" ${CalculationOperator.MINUS.symbol} ") }
            buttonDivide.setOnClickListener { presenter.addInputValue(" ${CalculationOperator.DIVIDE.symbol} ") }
            buttonMultiply.setOnClickListener { presenter.addInputValue(" ${CalculationOperator.MULTIPLY.symbol} ") }
        }
    }

    private fun setOnDeleteClickListener() {
        binding.buttonDelete.setOnClickListener {
            presenter.deleteLast()
        }
    }

    private fun setOnCalculateClickListener() {
        binding.buttonEquals.setOnClickListener {
            presenter.evaluate()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
