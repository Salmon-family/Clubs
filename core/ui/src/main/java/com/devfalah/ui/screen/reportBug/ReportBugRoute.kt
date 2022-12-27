package com.devfalah.ui.screen.reportBug

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE_REPORT_BUG = "reportBug"

fun NavGraphBuilder.reportBugRoute(navController: NavController){
    composable(route = ROUTE_REPORT_BUG){
        ReportBugScreen(navController)
    }
}