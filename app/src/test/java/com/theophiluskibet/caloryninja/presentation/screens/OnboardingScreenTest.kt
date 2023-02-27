package com.theophiluskibet.caloryninja.presentation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class OnboardingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @Test
//    fun `test button is displayed`() {
//        composeTestRule.setContent {
//            CaloryNinjaTheme {
//                OnboardingScreen({})
//            }
//        }
//        composeTestRule.onNodeWithText("Start").assertIsDisplayed()
//    }
}
