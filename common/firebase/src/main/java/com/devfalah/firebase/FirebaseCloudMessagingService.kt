package com.devfalah.firebase



import android.content.Intent
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
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
                notificationService.showNotification(applicationContext,friendId, title, description)
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

    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)
        if (isAppOnForeground()){
            Log.e("DEVFALAH",intent.toString())
        }else{
            Log.e("DEVFALAHNOT",intent.toString())
        }


    }
    companion object {
        val events = MutableSharedFlow<NotificationDataModel>()
        var newToken: String = ""
    }

    private fun isAppOnForeground(): Boolean {
        return ProcessLifecycleOwner.get().lifecycle.currentState
            .isAtLeast(Lifecycle.State.STARTED)
    }
}
