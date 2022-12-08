package com.devfalah.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.flow.update


class FirebaseCloudMessagingService : FirebaseMessagingService() {

    override  fun onMessageReceived(message: RemoteMessage) {
        //When App in background notification is handle by system
        // and it used notification payload and used title and body

        // and app in foreground i am using data payload

        // now send only data payload on that case onMessageRecievd also called in background.


        //Log incoming message
        Log.v("DEVFALAHMESSAGE", "From ${message.from}")

        //Log Data Payload
        if (message.data.isNotEmpty()) {
            Log.v("DEVFALAHMESSAGE", "Message Data ${message.data}")
        }

        //Check if message contains a notification payload

        message.data.let {data ->
            Log.v("DEVFALAHMESSAGE", "Message Data Body ${data["id"]}")
            Events.id.update { data["id"]?.toInt() ?:0  }
            //when app in forground that notification is not shown on status bar
            //lets write a code to display notification in status bar when app in forground.
        }



        if (message.notification != null) {
            Log.v("DEVFALAHMESSAGE", "Notification ${message.notification}")
            Log.v("DEVFALAHMESSAGE", "Notification Title ${message.notification!!.title}")
            Log.v("DEVFALAHMESSAGE", "Notification Body ${message.notification!!.body}")
            Log.e("DEVFALAHMESSAGE","DDDDDD"+Events.id.value.toString())

        }

    }

    override fun onNewToken(token: String) {
        Log.v("DEVFALAHMESSAGE", token)
    }
}