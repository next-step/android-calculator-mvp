package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.CalculationExpression
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity(), CalculatorViewInterface {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: CalculatorPresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(this)

        binding.button0.setOnClickListener { presenter.appendExpression(0) }
        binding.button1.setOnClickListener { presenter.appendExpression(1) }
        binding.button2.setOnClickListener { presenter.appendExpression(2) }
        binding.button3.setOnClickListener { presenter.appendExpression(3) }
        binding.button4.setOnClickListener { presenter.appendExpression(4) }
        binding.button5.setOnClickListener { presenter.appendExpression(5) }
        binding.button6.setOnClickListener { presenter.appendExpression(6) }
        binding.button7.setOnClickListener { presenter.appendExpression(7) }
        binding.button8.setOnClickListener { presenter.appendExpression(8) }
        binding.button9.setOnClickListener { presenter.appendExpression(9) }

        binding.buttonPlus.setOnClickListener { presenter.appendExpression(Operator.ADDITION) }
        binding.buttonMinus.setOnClickListener { presenter.appendExpression(Operator.SUBTRACT) }
        binding.buttonMultiply.setOnClickListener { presenter.appendExpression(Operator.MULTIPLY) }
        binding.buttonDivide.setOnClickListener { presenter.appendExpression(Operator.DIVIDE) }

        binding.buttonDelete.setOnClickListener { presenter.removeExpression() }

        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    override fun showCalculationResult(result: String) {
        binding.textView.text = result
    }

    override fun alertIncompleteExpression() {
        Toast.makeText(this, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showCalculationExpression(expression: String) {
        binding.textView.text = expression
    }
}
