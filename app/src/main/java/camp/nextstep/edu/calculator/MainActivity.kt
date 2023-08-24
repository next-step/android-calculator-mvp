package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun showResult(result: String) {
        binding.textView.text = result
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPresenter()
        initView()
        setContentView(binding.root)
    }

    private fun initPresenter() {
        presenter = MainPresenter(this)
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button0.setOnClickListener {
            presenter.clickButton("0")
        }

        binding.button1.setOnClickListener {
            presenter.clickButton("1")
        }

        binding.button2.setOnClickListener {
            presenter.clickButton("2")
        }

        binding.button3.setOnClickListener {
            presenter.clickButton("3")
        }

        binding.button4.setOnClickListener {
            presenter.clickButton("4")
        }

        binding.button5.setOnClickListener {
            presenter.clickButton("5")
        }

        binding.button6.setOnClickListener {
            presenter.clickButton("6")
        }

        binding.button7.setOnClickListener {
            presenter.clickButton("7")
        }

        binding.button8.setOnClickListener {
            presenter.clickButton("8")
        }

        binding.button9.setOnClickListener {
            presenter.clickButton("9")
        }

        binding.buttonPlus.setOnClickListener {
            presenter.clickButton(Operator.PLUS.operator)
        }

        binding.buttonMinus.setOnClickListener {
            presenter.clickButton(Operator.MINUS.operator)
        }

        binding.buttonMultiply.setOnClickListener {
            presenter.clickButton(Operator.TIMES.operator)
        }

        binding.buttonDivide.setOnClickListener {
            presenter.clickButton(Operator.DIV.operator)
        }

        binding.buttonDelete.setOnClickListener {
            presenter.removeLast()
        }

        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }
}
