package com.devfalah.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import javax.inject.Inject

import kotlin.random.Random

class Notifier @Inject constructor() {

    fun showNotification(context: Context, friedId: Int, title: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel =
                NotificationChannel(FirebaseCloudMessagingService.CHANNEL_ID, title, importance)
            mChannel.description = description
            val notificationManager =
                getSystemService(context, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
        val intent = Intent(context, Class.forName(FirebaseCloudMessagingService.CHAT_ACTIVITY))
        intent.putExtra(FirebaseCloudMessagingService.FRIEND_ID, friedId)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(context,
            FirebaseCloudMessagingService.CHANNEL_ID)
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