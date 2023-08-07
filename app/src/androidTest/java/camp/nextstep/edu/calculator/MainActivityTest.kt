package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
	@get:Rule
	val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

	@Test
	fun 피연산자를_클릭하면__피연산자가_그대로노출된다() {
		// when
		clickButton(R.id.button1)

		// then
		checkText(R.id.textView, "1")
	}

	@Test
	fun 빈값일때__연산자를_클릭하면__빈값이_노출된다() {
		// when
		clickButton(R.id.buttonPlus)

		// then
		checkText(R.id.textView, "")
	}

	@Test
	fun 빈값이_아닐때__연산자를_클릭하면__좌우공백과함께_연산자가_노출된다() {
		// given
		clickButton(R.id.button1)

		// when
		clickButton(R.id.buttonPlus)

		// then
		checkText(R.id.textView, "1 + ")
	}

	@Test
	fun 연산자가_입력되었을때__연산자를_클릭하면__연산자가_덮어씌여진다() {
		// given
		clickButton(R.id.button1)
		clickButton(R.id.buttonPlus)

		// when
		clickButton(R.id.buttonMultiply)

		// then
		checkText(R.id.textView, "1 * ")
	}

	@Test
	fun 빈값일때__삭제버튼을_클릭하면__빈값이_노출된다() {
		// when
		clickButton(R.id.buttonDelete)

		// then
		checkText(R.id.textView, "")
	}

	@Test
	fun 피연산자가_입력되었을때__삭제버튼을_클릭하면__피연산자가_제거된다() {
		// given
		clickButton(R.id.button1)
		clickButton(R.id.buttonPlus)
		clickButton(R.id.button2)

		// when
		clickButton(R.id.buttonDelete)

		// then
		checkText(R.id.textView, "1 + ")
	}

	@Test
	fun 연산자가_입력되었을때__삭제버튼을_클릭하면__좌우공백과함께_연산자가_제거된다() {
		// given
		clickButton(R.id.button1)
		clickButton(R.id.buttonPlus)

		// when
		clickButton(R.id.buttonDelete)

		// then
		checkText(R.id.textView, "1")
	}

	@Test
	fun 피연산자가_입력되었을때__eqauls_버튼을_클릭하면__계산된결과가_노출된다() {
		// given
		clickButton(R.id.button1)
		clickButton(R.id.buttonPlus)
		clickButton(R.id.button2)

		// when
		clickButton(R.id.buttonEquals)

		// then
		checkText(R.id.textView, "3")
	}

	@Test
	fun 연산자가_입력되었을때__equals_버튼을_클릭하면__계산식이_그대로_유지된다() {
		// given
		clickButton(R.id.button1)
		clickButton(R.id.buttonPlus)

		// when
		clickButton(R.id.buttonEquals)

		// then
		checkText(R.id.textView, "1 + ")
	}

	private fun clickButton(id: Int) {
		onView(withId(id)).perform(click())
	}

	private fun checkText(id: Int, text: String) {
		onView(withId(id)).check(matches(withText(text)))
	}
}