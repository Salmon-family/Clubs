package com.devfalah.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.composable.CreatePostItem
import com.devfalah.viewmodels.home.HomeViewModel

@Composable
fun HomeScreen(
    state: HomeViewModel = hiltViewModel()
) {
    HomeContent()
}

@Preview(showSystemUi = true)
@Composable
fun HomeContent(
) {
    LazyColumn {
        item {
            CreatePostItem(
                image = "",
                value = "hello",
                onTextChange = {}
            )
        }
    }
}