package com.theophiluskibet.caloryninja.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.theophiluskibet.caloryninja.data.remote.models.Calory
import com.theophiluskibet.caloryninja.data.remote.models.CaloryItem
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryViewModel
import com.theophiluskibet.caloryninja.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CaloryScreen(
    caloryViewModel: CaloryViewModel = getViewModel(),
    onNavigateToDetails: () -> Unit
) {
    val context = LocalContext.current
    var searchString by remember {
        mutableStateOf("")
    }
    var listOfFoods by remember {
        mutableStateOf(mutableListOf<ListOfFoods>())
    }

    val caloryUiState = caloryViewModel.calories.observeAsState().value

    Column() {
        TextField(
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
                    // searchString = ""
                    caloryViewModel.getCalories(searchString)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            }

        )
        Spacer(modifier = Modifier.height(20.dp))
        Content(uiState = caloryUiState, context = context, onClick = onNavigateToDetails)
    }

    // Toast.makeText(context, "Loading......", Toast.LENGTH_LONG).show()
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

data class ListOfFoods(
    val name: String
)

@Composable
fun SearchInput() {
}

@Composable
fun Content(uiState: UiState<Calory>?, context: Context, onClick: () -> Unit) {
    when (uiState) {
        is UiState.Error -> Toast.makeText(
            context,
            "${uiState.error}",
            Toast.LENGTH_LONG
        ).show()
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            if (uiState.data!!.caloryItems.isEmpty()) {
                EmptyScreen()
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
//                    LazyColumn() {
//                        items(uiState.data!!.caloryItems) { calory ->
//                            Column() {
//                                Text(text = calory.name)
//                                Spacer(modifier = Modifier.height(5.dp))
//                                Text(text = calory.calories.toString())
//                            }
//                        }
//                    }
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(uiState.data.caloryItems) {
                            CaloryCard(caloryItem = it, onClick = onClick)
                        }
                    }
                }
            }
        }
        else -> {
            EmptyScreen()
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "No data, please search")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CaloryCard(caloryItem: CaloryItem, onClick: () -> Unit) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Name: ${caloryItem.name}")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Calories: ${caloryItem.calories}")
        }
    }
}
