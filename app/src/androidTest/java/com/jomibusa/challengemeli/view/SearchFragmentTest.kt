package com.jomibusa.challengemeli.view

import android.content.Context
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.android.material.textfield.TextInputLayout
import com.jomibusa.challengemeli.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchFragmentTest {

    private val STRING_TO_BE_TYPED = "Computadora"

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val context: Context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun writeAndSearchAnItemThenGoToTheListProductsFragment() {

        val mockNavController = mock(NavController::class.java)

        val searchFragment =
            launchFragmentInContainer<SearchFragment>(themeResId = R.style.Theme_ChallengeMELI)

        searchFragment.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(withId(R.id.text_input_edit_text_search)).perform(
            ViewActions.replaceText(STRING_TO_BE_TYPED),
            ViewActions.closeSoftKeyboard()
        )

        onView(ViewMatchers.withId(R.id.material_button_search)).perform(ViewActions.click())

        verify(mockNavController).navigate(
            SearchFragmentDirections.actionSearchFragmentToListProductsFragment(
                STRING_TO_BE_TYPED
            )
        )
    }

    @Test
    fun searchEmptyItemAndValidateError() {

        val mockNavController = mock(NavController::class.java)

        val searchFragment =
            launchFragmentInContainer<SearchFragment>(themeResId = R.style.Theme_ChallengeMELI)

        searchFragment.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(ViewMatchers.withId(R.id.material_button_search)).perform(ViewActions.click())

        onView(withId(R.id.text_input_layout_search)).check(
            matches(
                hasTextInputLayoutErrorText(
                    context.getString(
                        R.string.text_validation_text_search
                    )
                )
            )
        )
    }

    private fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> =
        object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description?) = Unit

            override fun matchesSafely(item: View?): Boolean {
                if (item !is TextInputLayout) return false
                val error = item.error ?: return false
                val hint = error.toString()
                return expectedErrorText == hint
            }
        }
}