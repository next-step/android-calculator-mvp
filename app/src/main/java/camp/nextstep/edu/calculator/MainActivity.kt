package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.calculator.Num
import camp.nextstep.edu.domain.calculator.Operators


class MainActivity : AppCompatActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.appendExpression(Num(0)) }
        binding.button1.setOnClickListener { presenter.appendExpression(Num(1)) }
        binding.button2.setOnClickListener { presenter.appendExpression(Num(2)) }
        binding.button3.setOnClickListener { presenter.appendExpression(Num(3)) }
        binding.button4.setOnClickListener { presenter.appendExpression(Num(4)) }
        binding.button5.setOnClickListener { presenter.appendExpression(Num(5)) }
        binding.button6.setOnClickListener { presenter.appendExpression(Num(6)) }
        binding.button7.setOnClickListener { presenter.appendExpression(Num(7)) }
        binding.button8.setOnClickListener { presenter.appendExpression(Num(8)) }
        binding.button9.setOnClickListener { presenter.appendExpression(Num(9)) }

        binding.buttonPlus.setOnClickListener { presenter.appendExpression(Operators.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.appendExpression(Operators.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.appendExpression(Operators.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.appendExpression(Operators.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLastExpression() }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate { showError() }
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showError() {
        Toast.makeText(
            applicationContext,
            "완성되지 않은 수식입니다",
            Toast.LENGTH_SHORT
        ).show()
    }
}
