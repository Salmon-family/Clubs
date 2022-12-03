package com.devfalah.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClubsApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        ClubsNavGraph(navController = navController)
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
    
    BottomNavigation {
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
        modifier = Modifier.background(LightCardBackgroundColor).align(alignment = Alignment.CenterVertically),
        label = {Text(text = screen.title, overflow = TextOverflow.Ellipsis, maxLines = 1,) },
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
