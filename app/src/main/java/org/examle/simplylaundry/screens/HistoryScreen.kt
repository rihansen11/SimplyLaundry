package org.examle.simplylaundry.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavHostController,
    historyList: List<String>
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "List Laundry")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "kembali")
                    }
                }
            )
        }
    ){paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues = paddingValues),content = {
            items(historyList) { historyItem ->
                Text(text = historyItem, modifier = Modifier.padding(16.dp))
            }
        })
    }
}