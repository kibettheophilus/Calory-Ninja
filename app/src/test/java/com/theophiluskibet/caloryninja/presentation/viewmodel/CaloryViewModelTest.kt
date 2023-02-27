package com.theophiluskibet.caloryninja.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.utils.calories
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CaloryViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // subject under test
    private lateinit var caloryViewModel: CaloryViewModel

    // helpers
    private val repository: CaloryRepository = mockk()

    @Before
    fun setup() {
        caloryViewModel = CaloryViewModel(repository)
    }

    @Test
    fun `get saved calories update ui state loading`() = runTest {
        assertThat(caloryViewModel.calories.value).isNull()
    }

    @Test
    fun `get saved calories update ui state with data`() = runTest {
        caloryViewModel.getSavedCalories()

        assertThat(caloryViewModel.calories.value).isNotNull()
    }
}
