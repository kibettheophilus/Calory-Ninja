package com.theophiluskibet.caloryninja.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.theophiluskibet.caloryninja.presentation.navigation.AppNavHost
import com.theophiluskibet.caloryninja.presentation.ui.theme.CaloryNinjaTheme
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val caloryViewModel: CaloryViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryNinjaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("${caloryViewModel.calories.observeAsState().value}")
//
//                    Log.d("CALORIES", "CALORIESUI: ${caloryViewModel.calories.value}")
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CaloryNinjaTheme {
        Greeting("Android")
    }
}
