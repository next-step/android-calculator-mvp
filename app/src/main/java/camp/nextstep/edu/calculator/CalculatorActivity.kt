package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CalculatorPresenter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {
        binding.button0.setOnClickListener {
            presenter.updateExpressions(binding.button0.text.toString())
        }
        binding.button1.setOnClickListener {
            presenter.updateExpressions(binding.button1.text.toString())
        }
        binding.button2.setOnClickListener {
            presenter.updateExpressions(binding.button2.text.toString())
        }
        binding.button3.setOnClickListener {
            presenter.updateExpressions(binding.button3.text.toString())

        }
        binding.button4.setOnClickListener {
            presenter.updateExpressions(binding.button4.text.toString())
        }
        binding.button5.setOnClickListener {
            presenter.updateExpressions(binding.button5.text.toString())
        }
        binding.button6.setOnClickListener {
            presenter.updateExpressions(binding.button6.text.toString())
        }
        binding.button7.setOnClickListener {
            presenter.updateExpressions(binding.button7.text.toString())
        }
        binding.button8.setOnClickListener {
            presenter.updateExpressions(binding.button8.text.toString())
        }
        binding.button9.setOnClickListener {
            presenter.updateExpressions(binding.button9.text.toString())
        }
        binding.buttonDivide.setOnClickListener {
            presenter.updateExpressions(binding.buttonDivide.text.toString())
        }
        binding.buttonMinus.setOnClickListener {
            presenter.updateExpressions(binding.buttonMinus.text.toString())
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.updateExpressions(binding.buttonMultiply.text.toString())
        }
        binding.buttonPlus.setOnClickListener {
            presenter.updateExpressions(binding.buttonPlus.text.toString())
        }
        binding.buttonDelete.setOnClickListener {
            presenter.deleteLast()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculateAndReset()
        }
    }

    /** 수식을 화면에 출력 */
    override fun showExpression(expression: String) {
        binding.textView.text = expression.toString()
    }

    /** 에러 메세지 출력 */
    override fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
