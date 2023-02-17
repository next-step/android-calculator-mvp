package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Operator

class MainActivity : AppCompatActivity(), CalculateContract.View {
    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CalculateContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatePresenter(this)

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

        binding.buttonPlus.setOnClickListener { presenter.appendOperator(Operator.PLUS) }
        binding.buttonMinus.setOnClickListener { presenter.appendOperator(Operator.MINUS) }
        binding.buttonMultiply.setOnClickListener { presenter.appendOperator(Operator.MULTIPLY) }
        binding.buttonDivide.setOnClickListener { presenter.appendOperator(Operator.DIV) }

        binding.buttonDelete.setOnClickListener { presenter.removeLastValue() }
        binding.buttonEquals.setOnClickListener { presenter.calculateExpression() }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }
}
