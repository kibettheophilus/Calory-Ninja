package com.theophiluskibet.caloryninja.presentation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CaloryScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `test calories is displayed`() {
        composeTestRule.setContent {
        }
    }
}
