package blue_bay.app.features.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import blue_bay.app.R
import blue_bay.app.features.menu.SignInMenuActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Register {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignInMenuActivity::class.java)

    @Test
    fun register() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_login_menu_register), withText("Sign up")

            )
        )
        appCompatButton.perform(scrollTo(), click())

        val textInputEditText = onView(
            allOf(
                withParent(withParent(withParent(withId(R.id.et_register_email)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("fff@gmail.com"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withParent(withParent(withParent(withId(R.id.et_register_email)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText2.perform(pressImeActionButton())

        val textInputEditText3 = onView(
            allOf(
                withParent(withParent(withParent(withId(R.id.et_register_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("Qwerty"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withText("Qwerty"),
                withParent(withParent(withParent(withId(R.id.et_register_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText4.perform(pressImeActionButton())

        val textInputEditText5 = onView(
            allOf(
                withText("Qwerty"),
                withParent(withParent(withParent(withId(R.id.et_register_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("Qwerty1"))

        val textInputEditText6 = onView(
            allOf(
                withText("Qwerty1"),
                withParent(withParent(withParent(withId(R.id.et_register_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText6.perform(closeSoftKeyboard())

        val textInputEditText7 = onView(
            allOf(
                withParent(withParent(withParent(withId(R.id.et_register_repeat_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText7.perform(replaceText("Qwerty1"), closeSoftKeyboard())

        val textInputEditText8 = onView(
            allOf(
                withText("Qwerty1"),
                withParent(withParent(withParent(withId(R.id.et_register_repeat_password)))),
                withId(R.id.etProfileInput),
                isDisplayed()
            )
        )
        textInputEditText8.perform(pressImeActionButton())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_register_next), withText("Login")
            )
        )
        appCompatButton2.perform(scrollTo(), click())
    }

}
