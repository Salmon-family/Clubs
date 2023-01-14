package com.devfalah.firebase


import android.util.Log
import com.devfalah.repositories.CoreNotificationKeys
import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationKeys
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseCloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationService: Notifier

    override fun onMessageReceived(message: RemoteMessage) {
        message.data.let { data ->
            if (data.isNotEmpty()) {
                val id = (data[NotificationKeys.ID_KEY]?.toInt() ?: 0)
                val friendId = (data[NotificationKeys.FRIEND_ID_KEY]?.toInt() ?: 0)
                val title = data[NotificationKeys.TITLE].toString()
                val description = data[NotificationKeys.DESCRIPTION].toString()
                val clickAction = data[CoreNotificationKeys.CLICK_ACTION].toString()
                Log.e("testNotification", "clickAction: $clickAction")

                sendNotification(
                    clickAction = clickAction,
                    title = title,
                    description = description,
                    friendId = friendId
                )


                GlobalScope.launch {
                    if (clickAction == "newMessage") {
                        events.emit(NotificationDataModel(id = id, friendId = friendId))
                    }
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        newToken = token
    }

    private fun sendNotification(
        clickAction: String,
        title: String,
        description: String,
        friendId: Int
    ) {
        when (clickAction) {
            "friendRequest" -> notificationService.sendFriendRequestNotification(this, description)

            "newMessage" -> notificationService.sendNewMessageNotification(
                this,
                friendId,
                title,
                description
            )

            "acceptFriendRequest" -> notificationService.sendAcceptFriendRequestNotification(
                this,
                description
            )

            "acceptJoinClubRequest" -> notificationService.sendAcceptJoinClubRequestNotification(
                this,
                description
            )

            "joinClubRequest" -> notificationService.sendJoinClubRequestNotification(
                this,
                userName = description,
                clubName = title
            )

            "likePost" -> notificationService.sendLikePostNotification(this, description)

            "likeGroupPost" -> notificationService.sendLikeGroupPostNotification(
                this,
                description,
                title
            )


        }
    }

    companion object {
        val events = MutableSharedFlow<NotificationDataModel>()
        var newToken: String = ""
    }

}
