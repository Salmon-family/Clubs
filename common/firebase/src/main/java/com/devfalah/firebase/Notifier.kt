package com.devfalah.firebase

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.messaging.FirebaseMessagingService
import javax.inject.Inject
import kotlin.random.Random

class Notifier @Inject constructor() {

    fun showNotification(context: Context, friedId: Int, title: String, description: String) {
        if (!checkIfChatIsCurrentActivity(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel =
                    NotificationChannel(CHANNEL_ID, title, importance)
                mChannel.description = description
                val notificationManager =
                    getSystemService(context,
                        NotificationManager::class.java) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
            val intent = Intent(context, Class.forName(CHAT_ACTIVITY))
            intent.putExtra(FRIEND_ID, friedId)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT)
            val notification = NotificationCompat.Builder(context,
                CHANNEL_ID)
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
    }

    private fun checkIfChatIsCurrentActivity(context: Context): Boolean {
        val activityManager: ActivityManager =
            context.applicationContext.getSystemService(FirebaseMessagingService.ACTIVITY_SERVICE) as ActivityManager
        val currentActivity = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activityManager.appTasks[0].taskInfo.topActivity?.className
        } else {
            activityManager.getRunningTasks(1)[0].topActivity?.className
        }
        return currentActivity == CHAT_ACTIVITY
    }


    companion object {
        const val CHANNEL_ID = "gravity_fcm_channel"
        const val CHAT_ACTIVITY = "com.thechance.ui.ChatActivity"
        const val FRIEND_ID = "FRIEND_ID"
    }
}