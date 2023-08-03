package camp.nextstep.edu.calculator

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityCalculatorTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `빈문자가_입력된상태에서_1을클릭하면_1이입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = ""
        }

        onView(withId(R.id.button1)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun `5_더하기_입력된상태에서_1을클릭하면_5더하기1_이입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "5 + "
        }

        onView(withId(R.id.button1)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun `8이입력된상태에서_9를클릭하면_89가_이입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "8"
        }

        onView(withId(R.id.button9)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun `빈문자가_입력된상태에서_더하기를_클릭하면_빈문자그대로_보여진다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = ""
        }

        onView(withId(R.id.buttonPlus)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `1입력된상태에서_더하기를_클릭하면_1_더하기가_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "1"
        }

        onView(withId(R.id.buttonPlus)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("1 + ")))
    }

    @Test
    fun `1_더하기_가_입력된상태에서_뺴기를_클릭하면_1_뺴기가_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "1 + "
        }

        onView(withId(R.id.buttonMinus)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("1 - ")))
    }

    @Test
    fun `빈문자가_입력된상태에서_삭제를_클릭하면_빈문자그대로_보여진다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = ""
        }

        onView(withId(R.id.buttonDelete)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `32_더하기_1_입력된상태에서_삭제를_클릭하면_32_더하기가_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "32 + 1"
        }

        onView(withId(R.id.buttonDelete)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("32 + ")))
    }

    @Test
    fun `32_더하기_입력된상태에서_삭제를_클릭하면_32_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "32 + "
        }

        onView(withId(R.id.buttonDelete)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("32")))
    }

    @Test
    fun `32_입력된상태에서_삭제를_클릭하면_3_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "32"
        }

        onView(withId(R.id.buttonDelete)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun `3_더하기_2_입력된상태에서_등호_클릭하면_5_입력된다`() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<TextView>(R.id.textView).text = "3 + 2"
        }

        onView(withId(R.id.buttonEquals)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("5")))
    }
}
