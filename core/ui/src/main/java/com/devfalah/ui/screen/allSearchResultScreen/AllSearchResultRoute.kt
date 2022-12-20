package com.devfalah.ui.screen.allSearchResultScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.allSearchResult.AllSearchResultArgs

const val ALL_SEARCH_RESULT_SCREEN = "AllSearchResult_Screen"

fun NavController.navigateToAllSearchResult(title: String, searchType: Int, keyword: String) {
    navigate("${ALL_SEARCH_RESULT_SCREEN}/${title}/${searchType}/${keyword}")
}

fun NavGraphBuilder.allSearchResultRoute(navController: NavController) {
    composable(
        route = "${ALL_SEARCH_RESULT_SCREEN}/{${AllSearchResultArgs.Screen_Title_ARG}}/{${AllSearchResultArgs.Type_Search_ARG}}/{${AllSearchResultArgs.Search_Keyword}}",
        arguments = listOf(
            navArgument(AllSearchResultArgs.Screen_Title_ARG) { NavType.StringType },
            navArgument(AllSearchResultArgs.Type_Search_ARG) { NavType.IntType },
            navArgument(AllSearchResultArgs.Search_Keyword) { NavType.StringType }
        )

    ) {
        AllSearchResultScreenScreen(navController)
    }
}