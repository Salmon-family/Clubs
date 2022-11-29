package com.devfalah.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devfalah.ui.composable.CreatePostItem

@Composable
fun HomeScreen(
) {
    HomeContent()
}

@Preview(showSystemUi = true)
@Composable
fun HomeContent() {
    LazyColumn{
       item{
           CreatePostItem(
               image = "",
               value = "hello",
               onTextChange = {}
           )
       }
    }
}