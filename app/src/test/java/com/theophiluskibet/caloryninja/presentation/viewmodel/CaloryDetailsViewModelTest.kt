package com.theophiluskibet.caloryninja.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CaloryDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // subject under test
    private lateinit var caloryDetailsViewModel: CaloryDetailsViewModel

    // helpers
    private val repository: CaloryRepository = mockk()

    @Before
    fun setup() {
        caloryDetailsViewModel = CaloryDetailsViewModel(repository)
    }

    @Test
    fun `get calory updates ui state loading`() = runTest {
        assertThat(caloryDetailsViewModel.caloryState.value).isNull()
    }

    @Test
    fun `get calory by name updates UIState with correct value`() = runTest {
        caloryDetailsViewModel.getCalory("rice")

        assertThat(caloryDetailsViewModel.caloryState.value).isNotNull()
    }
}
