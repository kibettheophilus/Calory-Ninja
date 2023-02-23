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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
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
        Text(
            text = "Calories: ${calory!!.calories}",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Carbohydrates: ${calory!!.carbohydratesTotalGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Cholesterol: ${calory!!.cholesterolMilliGrams}MilliGrams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Saturated Fats: ${calory!!.fatSaturatedGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Total Fats: ${calory!!.fatTotalGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Fiber: ${calory!!.fiberGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Potassium: ${calory!!.potassiumMilliGrams}MilliGrams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Protein: ${calory!!.proteinGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Serving Size: ${calory!!.servingSizeGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sodium: ${calory!!.sodiumMilliGrams}MilliGrams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sugar: ${calory!!.sugarGrams}Grams",
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
    }
}
