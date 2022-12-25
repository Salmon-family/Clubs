package com.devfalah.ui

sealed class Screen(
    var title: Int,
    var iconSelected: Int = 0,
    var iconUnselected: Int = 0,
    var screen_route: String
) {
    object Home : Screen( R.string.home, R.drawable.ic_home, R.drawable.ic_home_outline, "home")
    object Search : Screen( R.string.search, R.drawable.ic_search_filld, R.drawable.ic_search_outline, "search")
    object Clubs : Screen( R.string.clubs, R.drawable.ic_clubs_filled, R.drawable.ic_clubs_outline, "clubs")
    object Notification : Screen( R.string.notification, R.drawable.ic_notification, R.drawable.ic_notification_outline, "notification")
    object Menu : Screen(R.string.menu, R.drawable.ic_menu_filled, R.drawable.ic_menu_outline, "menu")
}
