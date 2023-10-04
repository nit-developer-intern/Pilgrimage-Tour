package `in`.tourism.pilgrimagetour

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import `in`.tourism.pilgrimagetour.view.MainActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

        if (message.notification != null) {
            var newTitle = ""
            if(message.notification?.title?.contains("Pilgrim") == true){

                newTitle = if(Firebase.auth.currentUser != null){
                    val currentUser = Firebase.auth.currentUser
                    message.notification?.title?.replace("Pilgrim" , "${currentUser?.displayName}")
                        .toString()
                } else {
                    message.notification?.title!!
                }
            }
            showNotification("Hey Traveller", message.notification?.body)
        }
    }

    private fun showNotification(title: String?, body: String?)  {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title).setContentText(body).setAutoCancel(true).setSound(soundUri)
                .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }



}