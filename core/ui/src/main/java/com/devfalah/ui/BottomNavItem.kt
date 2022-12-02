package com.devfalah.ui

sealed class BottomNavItem(
    var title:String,
    var icon:Int,
    var screen_route:String
    ){
    object Home: BottomNavItem("Home",R.drawable.ic_home,"home")
    object Notification: BottomNavItem("Notification",R.drawable.ic_notification,"notification")
}
