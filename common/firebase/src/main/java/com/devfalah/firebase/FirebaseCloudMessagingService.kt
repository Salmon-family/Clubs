package com.devfalah.firebase


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
                notificationService.showNotification(applicationContext,
                    friendId,
                    title,
                    description)
                GlobalScope.launch {
                    events.emit(
                        NotificationDataModel(
                            id = id,
                            friendId = friendId,
                        )
                    )
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        newToken = token
    }

    companion object {
        val events = MutableSharedFlow<NotificationDataModel>()
        var newToken: String = ""
    }

}
