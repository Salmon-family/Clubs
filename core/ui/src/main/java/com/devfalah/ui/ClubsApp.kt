package com.devfalah.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.composable.MarqueeText
import com.devfalah.ui.theme.LightPrimaryBrandColor


@Composable
fun ClubsApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val visibility = currentRoute(navController) in listOf(
                Screen.Home.screen_route,
                Screen.Search.screen_route,
                Screen.Clubs.screen_route,
                Screen.Notification.screen_route,
                Screen.Menu.screen_route
            )
            BottomBar(navController = navController, visibility)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ClubsNavGraph(navController = navController)
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomBar(navController: NavHostController, visibility: Boolean) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Clubs,
        Screen.Notification,
        Screen.Menu
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    if (visibility) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background
        ) {
            items.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentRoute,
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screen_route } == true
    BottomNavigationItem(
        label = {
            MarqueeText(title = stringResource(id = screen.title))
        },
        alwaysShowLabel = false,
        icon = {
            Icon(
                painterResource(
                    id = if (selected) screen.iconSelected else screen.iconUnselected
                ),
                contentDescription = stringResource(id = screen.title)
            )
        },
        selected = selected,
        selectedContentColor = LightPrimaryBrandColor,
        unselectedContentColor = MaterialTheme.colors.secondaryVariant,
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

