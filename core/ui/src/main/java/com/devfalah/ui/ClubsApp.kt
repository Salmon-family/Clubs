package com.devfalah.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.screen.postDetails.PostDetailsScreen
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor


@Composable
fun ClubsApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ClubsNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Clubs,
        Screen.Notification,
        Screen.Menu
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    
    BottomNavigation(
        backgroundColor = LightCardBackgroundColor
    ) {
        items.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentRoute, navController = navController)
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screen_route } == true
    BottomNavigationItem(
        label = {Text(text = screen.title,softWrap = false) },
        alwaysShowLabel = false,
        icon = { Icon(
                painterResource(id =
                if (selected) screen.iconSelected else screen.iconUnselected),
                    contentDescription = screen.title
            )
        },
        selected = selected,
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
