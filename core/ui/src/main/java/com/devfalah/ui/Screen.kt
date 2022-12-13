package com.devfalah.ui

sealed class Screen(
    var title: String,
    var iconSelected: Int = 0,
    var iconUnselected: Int = 0,
    var screen_route: String
) {
    object Home : Screen("Home", R.drawable.ic_home, R.drawable.ic_home_outline, "home")
    object Search : Screen("Search", R.drawable.ic_search_filld, R.drawable.ic_search_outline, "search")
    object Clubs : Screen("Clubs", R.drawable.ic_clubs_filled, R.drawable.ic_clubs_outline, "clubs")
    object Notification : Screen("Notification", R.drawable.ic_notification, R.drawable.ic_notification_outline, "notification")
    object Menu : Screen("Menu", R.drawable.ic_menu_filled, R.drawable.ic_menu_outline, "menu")

    object Profile : Screen(title = "Profile Screen", screen_route = "ProfileScreen")
    object FriendRequestRoute : Screen(title = "Friend Request", screen_route = "FriendRequest")
    object CreatePost : Screen(title = "Create Post", screen_route = "CreatePost")
    object Friends : Screen(title = "Friends Screen", screen_route = "AllFriendsScreen")


}
