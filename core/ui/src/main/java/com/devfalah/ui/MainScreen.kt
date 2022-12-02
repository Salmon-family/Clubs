package com.devfalah.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import kotlinx.coroutines.selects.select


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Notification,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    
    BottomNavigation {
        items.forEach { items ->
            AddItem(screen = items, currentDestination = currentRoute, navController = navController)
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        modifier = Modifier.background(LightCardBackgroundColor),
        label = { Text(text = screen.title) },
        icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.title) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.screen_route }  == true,
        selectedContentColor = LightPrimaryBrandColor,
        unselectedContentColor = LightTernaryBlackColor,
        onClick = {
            navController.navigate(screen.screen_route) {
                navController.graph.startDestinationRoute?.let { screen_route ->
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }

    )

}
