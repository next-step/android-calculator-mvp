package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Expression
import com.example.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.appendOperandInExpression(0) }
        binding.button1.setOnClickListener { presenter.appendOperandInExpression(1) }
        binding.button2.setOnClickListener { presenter.appendOperandInExpression(2) }
        binding.button3.setOnClickListener { presenter.appendOperandInExpression(3) }
        binding.button4.setOnClickListener { presenter.appendOperandInExpression(4) }
        binding.button5.setOnClickListener { presenter.appendOperandInExpression(5) }
        binding.button6.setOnClickListener { presenter.appendOperandInExpression(6) }
        binding.button7.setOnClickListener { presenter.appendOperandInExpression(7) }
        binding.button8.setOnClickListener { presenter.appendOperandInExpression(8) }
        binding.button9.setOnClickListener { presenter.appendOperandInExpression(9) }

        binding.buttonPlus.setOnClickListener { presenter.appendOperatorInExpression(Operator.PLUS) }
        binding.buttonMinus.setOnClickListener { presenter.appendOperatorInExpression(Operator.MINUS) }
        binding.buttonMultiply.setOnClickListener { presenter.appendOperatorInExpression(Operator.MULTI) }
        binding.buttonDivide.setOnClickListener { presenter.appendOperatorInExpression(Operator.DIVIDE) }

        binding.buttonDelete.setOnClickListener { presenter.removeLastValueInExpression() }
        binding.buttonEquals.setOnClickListener { binding.textView.text = presenter.returnResult() }
    }

    override fun showToastMessage(expression: Expression) {
        Toast.makeText(
            applicationContext,
            "The '${expression.value()}' is not a valid formula.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.value()
    }
}
