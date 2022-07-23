package edu.nextstep.camp.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import edu.nextstep.camp.calculator.AndroidUITestHelper.onClickViewWithResId
import edu.nextstep.camp.calculator.AndroidUITestHelper.onClickViewsWithResIds
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun memory_버튼을_누르면_기록화면이_나와야한다() {
        onClickViewsWithResIds(R.id.button1, R.id.buttonPlus, R.id.button2, R.id.buttonEquals)

        onClickViewWithResId(R.id.buttonMemory)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun memory_버튼을_누르면_기록화면이_나온채_다시_버튼을_누르면_기록화면이_사라져야한다() {
        onClickViewsWithResIds(R.id.button1, R.id.buttonPlus, R.id.button2, R.id.buttonEquals)

        onClickViewWithResId(R.id.buttonMemory)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onClickViewWithResId(R.id.buttonMemory)
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }
}