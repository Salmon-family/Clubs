package com.devfalah.firebase

import android.util.Log
import com.devfalah.repository.models.NotificationDataModel
import com.devfalah.repository.models.NotificationDtoKeys
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class FirebaseCloudMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        message.data.let { data ->
            if (data.isNotEmpty()) {
                val id = (data[NotificationDtoKeys.ID_KEY]?.toInt() ?: 0)
                val friendId = (data[NotificationDtoKeys.FRIEND_ID_KEY]?.toInt() ?: 0)
                val messageText = (data[NotificationDtoKeys.MESSAGE_TEXT_KEY]).toString()
                val time = (data[NotificationDtoKeys.TIME_KEY]).toString()

                GlobalScope.launch {
                    events.emit(
                        NotificationDataModel(
                            id = id,
                            friendId = friendId,
                            messageText = messageText,
                            time = time,
                        )
                    )
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        Log.v("DEVFALAHMESSAGE", token)
    }

    companion object{
        val events = MutableSharedFlow<NotificationDataModel>()
    }

}
