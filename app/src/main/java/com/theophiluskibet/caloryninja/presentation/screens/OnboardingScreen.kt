package com.theophiluskibet.caloryninja.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theophiluskibet.caloryninja.R

@Composable
fun OnboardingScreen(onNavigateToHome: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Search for food calories", style = TextStyle(fontSize = 50.sp))
        Image(painter = painterResource(id = R.drawable.search), contentDescription = "")
        // Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNavigateToHome,
            border = BorderStroke(1.dp, Color.Cyan),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Cyan)
        ) {
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen({})
}
