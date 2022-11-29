package com.devfalah.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.R
import com.devfalah.ui.composable.CreatePostItem
import com.devfalah.ui.composable.TopBarComposable
import com.devfalah.viewmodels.home.HomeViewModel

@Composable
fun HomeScreen(
    state: HomeViewModel = hiltViewModel()
) {
    HomeContent()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun HomeContent(
) {
    Scaffold(
        topBar = {
           TopBarComposable(title = "Home")
        },
    ) {
        CreatePostItem(image = "", value = "", onTextChange = {})
    }
}

