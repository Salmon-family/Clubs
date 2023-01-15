package com.devfalah.firebase

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.messaging.FirebaseMessagingService
import javax.inject.Inject
import kotlin.random.Random

class Notifier @Inject constructor() {

    fun sendFriendRequestNotification(context: Context, name: String) {
        val title = context.getString(R.string.friend_request)
        val description = context.getString(R.string.sent_you_friend_request, name)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendAcceptFriendRequestNotification(context: Context, name: String) {
        val title = context.getString(R.string.accepted_friend_request)
        val description = context.getString(R.string.accept_your_friend_request, name)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendAcceptJoinClubRequestNotification(context: Context, clubName: String) {
        val title = context.getString(R.string.accepted_join_club_request)
        val description = context.getString(R.string.your_join_club_request_has_been_accepted, clubName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendJoinClubRequestNotification(context: Context, userName: String, clubName: String) {
        val title = context.getString(R.string.join_club_request)
        val description = context.getString(R.string.requested_to_join_club, userName, clubName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)

    }

    fun sendLikePostNotification(context: Context, userName: String) {
        val title = context.getString(R.string.like_post_title)
        val description = context.getString(R.string.liked_post_body, userName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendLikeGroupPostNotification(context: Context, userName: String, clubName: String) {
        val title = context.getString(R.string.like_post_title)
        val description = context.getString(R.string.liked_group_post, userName, clubName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendCommentPostNotification(context: Context, userName: String) {
        val title = context.getString(R.string.comment_on_post)
        val description = context.getString(R.string.commented_on_your_post_body, userName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendCommentClubPostNotification(context: Context, clubName: String, userName: String) {
        val title = context.getString(R.string.comment_on_post)
        val description = context.getString(R.string.commented_on_your_group_post, userName, clubName)
        val pendingIntent = getCoreActivityIntent(context)
        showNotification(context, title, description, pendingIntent)
    }

    fun sendNewMessageNotification(
        context: Context,
        friedId: Int,
        title: String,
        description: String
    ) {
        if (!checkIfChatIsCurrentActivity(context)) {
            val pendingIntent = getChatActivityIntent(context, friedId)
            showNotification(context, title, description, pendingIntent)
        }
    }

    private fun showNotification(
        context: Context,
        title: String,
        description: String,
        pendingIntent: PendingIntent? = null
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context, title, description)
        }
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        val manager =
            getSystemService(context, NotificationManager::class.java) as NotificationManager
        manager.notify(Random.nextInt(), notification)

    }

    private fun getCoreActivityIntent(context: Context): PendingIntent {
        val intent = Intent(context, Class.forName(CORE_ACTIVITY))
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT or FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT)
        }
    }


    private fun getChatActivityIntent(context: Context, friedId: Int): PendingIntent {
        val intent = Intent(context, Class.forName(CHAT_ACTIVITY))
        intent.putExtra(FRIEND_ID, friedId)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT or FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context, title: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(CHANNEL_ID, title, importance)
        mChannel.description = description
        val notificationManager =
            getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    private fun checkIfChatIsCurrentActivity(context: Context): Boolean {
        val activityManager: ActivityManager =
            context.applicationContext.getSystemService(FirebaseMessagingService.ACTIVITY_SERVICE) as ActivityManager
        val currentActivity = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activityManager.appTasks.getOrNull(0)?.taskInfo?.topActivity?.className
        } else {
            activityManager.getRunningTasks(1)[0].topActivity?.className
        }
        return currentActivity == CHAT_ACTIVITY
    }


    companion object {
        const val CHANNEL_ID = "gravity_fcm_channel"
        const val CHAT_ACTIVITY = "com.thechance.ui.ChatActivity"
        const val CORE_ACTIVITY = "com.devfalah.ui.main.MainActivity"
        const val FRIEND_ID = "FRIEND_ID"
    }
}