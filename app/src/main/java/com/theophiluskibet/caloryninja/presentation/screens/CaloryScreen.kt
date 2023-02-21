package com.theophiluskibet.caloryninja.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryViewModel
import com.theophiluskibet.caloryninja.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CaloryScreen(caloryViewModel: CaloryViewModel = getViewModel()) {
    val context = LocalContext.current

    val caloryUiState = caloryViewModel.calories.observeAsState().value

    when {
        caloryUiState is UiState.Error -> {
            Toast.makeText(context, "${caloryUiState.error}", Toast.LENGTH_LONG).show()
        }
        caloryUiState is UiState.Loading -> {
            Toast.makeText(context, "Loading......", Toast.LENGTH_LONG).show()
        }
        caloryUiState is UiState.Success -> {
            LazyColumn() {
                items(caloryUiState.data!!.caloryItems) { calory ->
                    Column() {
                        Text(text = calory.name)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = calory.calories.toString())
                    }
                }
            }
        }
    }
}
