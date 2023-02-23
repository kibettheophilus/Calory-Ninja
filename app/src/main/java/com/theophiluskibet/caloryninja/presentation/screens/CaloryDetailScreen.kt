package com.theophiluskibet.caloryninja.presentation.screens

import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryDetailsViewModel
import com.theophiluskibet.caloryninja.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CaloryDetailScreen(
    food: String?,
    caloryDetailsViewModel: CaloryDetailsViewModel = getViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        caloryDetailsViewModel.getCalory(food.toString())
    }

    val uiState = caloryDetailsViewModel.caloryState.observeAsState().value
    when {
        uiState is UiState.Success -> {
            Text(text = "${uiState.data?.calories}")
        }
        uiState is UiState.Loading -> {
            LoadingScreen()
        }
        uiState is UiState.Error -> {
            Toast.makeText(context, "${uiState.error}", Toast.LENGTH_LONG).show()
        }
    }
}
