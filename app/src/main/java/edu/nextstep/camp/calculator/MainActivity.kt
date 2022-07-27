package edu.nextstep.camp.calculator

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mPresenter: MainPresenter
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mPresenter = MainPresenter(this)
        mContext = this
        setContentView(binding.root)

        executeButtonClickAction()
    }

    private fun executeButtonClickAction() {

        // 숫자 클릭
        binding.button0.setOnClickListener { mPresenter.inputNumber(0) }
        binding.button1.setOnClickListener { mPresenter.inputNumber(1) }
        binding.button2.setOnClickListener { mPresenter.inputNumber(2) }
        binding.button3.setOnClickListener { mPresenter.inputNumber(3) }
        binding.button4.setOnClickListener { mPresenter.inputNumber(4) }
        binding.button5.setOnClickListener { mPresenter.inputNumber(5) }
        binding.button6.setOnClickListener { mPresenter.inputNumber(6) }
        binding.button7.setOnClickListener { mPresenter.inputNumber(7) }
        binding.button8.setOnClickListener { mPresenter.inputNumber(8) }
        binding.button9.setOnClickListener { mPresenter.inputNumber(9) }

        // 연산자 클릭
        binding.buttonPlus.setOnClickListener { mPresenter.inputOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { mPresenter.inputOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { mPresenter.inputOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { mPresenter.inputOperator(Operator.Divide) }

        // 삭제, 등호 클릭
        binding.buttonDelete.setOnClickListener { mPresenter.removeLastIndexData() }
        binding.buttonEquals.setOnClickListener { mPresenter.calculateInputValue() }
    }

    override fun showCalculateExpression(expressionStr: String) {
        binding.textView.text = expressionStr
    }

    override fun showCompletionOfExpressionDataMessage() {
        Toast.makeText(mContext, "수식을 다시 입력해주세요!", Toast.LENGTH_SHORT).show()
    }

}
