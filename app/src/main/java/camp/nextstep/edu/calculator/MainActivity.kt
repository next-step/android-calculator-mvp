package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initButtonClickListener()
    }

    /**
     * 버튼 Set Click Listener
     * */
    private fun initButtonClickListener() {
        binding.button0.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button1.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button2.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button3.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button4.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button5.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button6.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button7.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button8.setOnClickListener(onOperationAndOperatorClickListener)
        binding.button9.setOnClickListener(onOperationAndOperatorClickListener)

        binding.buttonPlus.setOnClickListener(onOperationAndOperatorClickListener)
        binding.buttonMinus.setOnClickListener(onOperationAndOperatorClickListener)
        binding.buttonMultiply.setOnClickListener(onOperationAndOperatorClickListener)
        binding.buttonDivide.setOnClickListener(onOperationAndOperatorClickListener)

        binding.buttonDelete.setOnClickListener(onDeleteClickListener)
        binding.buttonEquals.setOnClickListener(onEqualsClickListener)
    }

    private val onOperationAndOperatorClickListener : View.OnClickListener = View.OnClickListener { view ->
        val expression = when (view) {
            binding.button0 -> 0
            binding.button1 -> 1
            binding.button2 -> 2
            binding.button3 -> 3
            binding.button4 -> 4
            binding.button5 -> 5
            binding.button6 -> 6
            binding.button7 -> 7
            binding.button8 -> 8
            binding.button9 -> 9
            binding.buttonPlus -> Operator.Plus
            binding.buttonMinus -> Operator.Minus
            binding.buttonMultiply -> Operator.MultiBy
            binding.buttonDivide -> Operator.DividedBy
            else -> throw IllegalArgumentException("Invalid view")
        }
        presenter.addExpression(expression)
    }

    /**
     * Equals Click Listener
     * */
    private val onEqualsClickListener = View.OnClickListener {
        presenter.callEquals()
    }

    /**
     * Equals Click Listener
     * */
    private val onDeleteClickListener = View.OnClickListener {
        presenter.callDelete()
    }

    override fun showResult(result: String) {
        binding.textView.text = result
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }
}