package com.composeproject.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.composeproject.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var inputValue by remember {
        mutableStateOf("")
    }
    val list = listOf(
        state.value.inputValue,
        "ali",
        "ali",
        "ali",
        "ali",
        "ali",
        "ali",
        "ali",
        "ali",
        )
    var counterValue by remember {
        mutableStateOf(0)
    }
    Scaffold(snackbarHost = {
        SnackbarHost(modifier = Modifier, hostState = snackbarHostState)
    },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Home")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.setEvent(HomeViewEvent.OnSubmitClicked)
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navHostController.navigate("/details/${inputValue.ifEmpty { "No data assigned yet!" }}") }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        }
    ) { paddingValues ->
       LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
           items(count = list.size){index ->
               Text(text = "${list[index]}")
           }
           item {

               TextField(

                   leadingIcon = { Icon(imageVector = Icons.Filled.Add, contentDescription =null )},
                   value = inputValue, onValueChange = { newValue ->
                       inputValue = newValue
                   })
               Card(
                   modifier = Modifier
                       .padding(vertical = 24.dp, horizontal = 24.dp),
                   shape = MaterialTheme.shapes.medium
               ) {
                   Row (verticalAlignment = Alignment.CenterVertically,){
                       IconButton(onClick = {
                           counterValue++
                       }) {
                           Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                       }
                       Text(text = counterValue.toString())
                       IconButton(onClick = {
                           counterValue--
                       }) {
                           Icon(painter = painterResource(id = R.drawable.ic_remove), contentDescription = null)
                       }

                   }

               }
           }
       }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeViewEffect.LoadingDataSuccess -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Loading Data Success!",
                        duration = SnackbarDuration.Short,
                        actionLabel = "Info",
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        //do something in case action selected
                    }
                }

                is HomeViewEffect.LoadingDataFailed -> {

                }
            }
        }
    }
}