package com.theophiluskibet.caloryninja.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.presentation.components.CaloryDetailText
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
            DetailsHolder(calory = uiState.data)
        }
        uiState is UiState.Loading -> {
            LoadingScreen()
        }
        uiState is UiState.Error -> {
            Toast.makeText(context, "${uiState.error}", Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun DetailsHolder(calory: CaloryEntity?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = calory!!.name.replaceFirstChar(Char::titlecase),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = TextStyle(color = Color.Black, fontSize = 30.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        CaloryDetailText(text = "Calories: ${calory.calories}")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Carbohydrates: ${calory.carbohydratesTotalGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Cholesterol: ${calory.cholesterolMilliGrams}MilliGrams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Saturated Fats: ${calory.fatSaturatedGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Total Fats: ${calory.fatTotalGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Fiber: ${calory.fiberGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Potassium: ${calory.potassiumMilliGrams}MilliGrams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Protein: ${calory.proteinGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Serving Size: ${calory.servingSizeGrams}Grams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Sodium: ${calory.sodiumMilliGrams}MilliGrams")

        Spacer(modifier = Modifier.height(10.dp))

        CaloryDetailText(text = "Sugar: ${calory.sugarGrams}Grams")
    }
}
