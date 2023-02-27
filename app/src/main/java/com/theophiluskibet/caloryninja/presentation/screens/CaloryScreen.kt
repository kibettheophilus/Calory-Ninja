package com.theophiluskibet.caloryninja.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.presentation.components.LoadingScreen
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryViewModel
import com.theophiluskibet.caloryninja.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CaloryScreen(
    caloryViewModel: CaloryViewModel = getViewModel(),
    navController: NavController
) {
    var searchString by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        caloryViewModel.getSavedCalories()
    }
    val caloryUiState = caloryViewModel.caloriesState.observeAsState().value

    Column(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = searchString,
            onValueChange = {
                searchString = it
            },
            modifier = Modifier.fillMaxWidth(),

            placeholder = {
                Text(text = "Search for food")
            },
            trailingIcon = {
                IconButton(onClick = {
                    caloryViewModel.getCalories(searchString)
                    // searchString = ""
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Black),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                caloryViewModel.getCalories(searchString)
                // searchString = ""
            })
        )
        Spacer(modifier = Modifier.height(20.dp))
        CaloryListSection(uiState = caloryUiState, navController = navController)
    }
}

@Composable
fun CaloryListSection(uiState: UiState<List<CaloryEntity>>?, navController: NavController) {
    when (uiState) {
        is UiState.Error -> {
            EmptyScreen(text = "Please check your internet and search again")
        }
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            if (uiState.data!!.isEmpty()) {
                EmptyScreen(text = "No data, Please search")
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(uiState.data) {
                            CaloryCard(caloryItem = it, navController = navController)
                        }
                    }
                }
            }
        }
        else -> {
            EmptyScreen(text = "No data, Please search")
        }
    }
}

@Composable
fun EmptyScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CaloryCard(caloryItem: CaloryEntity, navController: NavController) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp),
        onClick = { navController.navigate("details/${caloryItem.name}") },
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Name: ${caloryItem.name}")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Calories: ${caloryItem.calories}")
        }
    }
}
