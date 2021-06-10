package com.expertapps.connect.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaMetadata
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.lifecycle.LifecycleCoroutineScope
import com.morse.common.R
import com.morse.common.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class NotificationAction(
    val actionname: String,
    @DrawableRes val actionImage: Int,
    val notificatoinPendingIntent: PendingIntent? = null
)

data class Track(val title: String, val artist: String, @DrawableRes val image: Int)

data class MessageContent(
    val messageContent: String,
    val messangerName: String,
    val timeStamp: Long = System.currentTimeMillis()
)

object LOADING {
    val LOADING_NOTIFICATION_ID = 0
    val LOADING_NOTIFICATION_CHANNEL_NAME = "LOADING-CHANNEL-NAME"
    val LOADING_NOTIFICATION_CHANNEL_ID = "LOADING-CHANNEL-ID"
    val LOADING_NOTIFICATION_CHANNEL_DESCIBTION = "LOADING-CHANNEL-DESCRIBTION"
}

object DEFAULT {
    val DEFAULT_NOTIFICATION_ID = 2
    val DEFAULT_NOTIFICATION_CHANNEL_NAME = "DEFAULT-CHANNEL-NAME"
    val DEFAULT_NOTIFICATION_CHANNEL_ID = "DEFAULT-CHANNEL-ID"
    val DEFAULT_NOTIFICATION_CHANNEL_DESCIBTION = "DEFAULT-CHANNEL-DESCRIBTION"
}

object ACTION {
    val DEFAULT_WITH_ACTION_NOTIFICATION_ID = 4
    val DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_NAME = "DEFAULT_WITH_ACTION-CHANNEL-NAME"
    val DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_ID = "DEFAULT_WITH_ACTION-CHANNEL-ID"
    val DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_DESCIBTION =
        "DEFAULT_WITH_ACTION-CHANNEL-DESCRIBTION"
}

object BIG_CONTENT {
    val DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_ID = 6
    val DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_NAME =
        "DEFAULT_WITH_BIG_CONTENT-CHANNEL-NAME"
    val DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_ID =
        "DEFAULT_WITH_BIG_CONTENT-CHANNEL-ID"
    val DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_DESCIBTION =
        "DEFAULT_WITH_BIG_CONTENT-CHANNEL-DESCRIBTION"
}

object BIG_IMAGE_CONTENT {
    val DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_ID = 8
    val DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_NAME =
        "DEFAULT_WITH_BIG_IMAGE_CONTENT-CHANNEL-NAME"
    val DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_ID =
        "DEFAULT_WITH_BIG_IMAGE_CONTENT-CHANNEL-ID"
    val DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_DESCIBTION =
        "DEFAULT_WITH_BIG_IMAGE_CONTENT-CHANNEL-DESCRIBTION"
}

object MEDIA {
    val MUSIC_NOTIFICATION_ID = 10
    val MUSIC_NOTIFICATION_CHANNEL_NAME =
        "MUSIC-CHANNEL-NAME"
    val MUSIC_NOTIFICATION_CHANNEL_ID =
        "MUSIC-CHANNEL-ID"
    val MUSIC_NOTIFICATION_CHANNEL_DESCIBTION =
        "MUSIC-CHANNEL-DESCRIBTION"
}

object CHAT {
    val CHAT_NOTIFICATION_ID = 12
    val CHAT_NOTIFICATION_CHANNEL_NAME =
        "CHAT-CHANNEL-NAME"
    val CHAT_NOTIFICATION_CHANNEL_ID =
        "CHAT-CHANNEL-ID"
    val CHAT_NOTIFICATION_CHANNEL_DESCIBTION =
        "CHAT-CHANNEL-DESCRIBTION"
}


class NotificationUtil private constructor(private val context: Context) {

    private var notificationManager: NotificationManager
    private var soundUri : Uri
    init {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    }

    // For Loading Notification

    fun notifyWithLoading(
        title: String? = null,
        message: String? = null,
        @DrawableRes notificationSmallIcon: Int,
    ) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                LOADING.LOADING_NOTIFICATION_CHANNEL_ID,
                LOADING.LOADING_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        var notification =
            NotificationCompat.Builder(context, LOADING.LOADING_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(notificationSmallIcon)
                .setContentTitle(
                    title
                )
                .setContentText(message)
                .setAutoCancel(false)
        notification.setProgress(100, 0, true)
        notificationManager.notify(LOADING.LOADING_NOTIFICATION_ID, notification.build())
    }

    fun cancelNotifyWithLoading(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(LOADING.LOADING_NOTIFICATION_ID)
        }
    }


    // For Default Style Notification

    fun notifyWithDefaultStyle(
        context: Context,
        title: String? = null,
        message: String? = null,
        @DrawableRes notificationSmallIcon: Int

    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                DEFAULT.DEFAULT_NOTIFICATION_CHANNEL_ID,
                DEFAULT.DEFAULT_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            notificationChannel.description = DEFAULT.DEFAULT_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, attributes)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder =
            NotificationCompat.Builder(context, DEFAULT.DEFAULT_NOTIFICATION_CHANNEL_NAME)
        notificationBuilder.setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(notificationSmallIcon) // Small Notification  image
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setSound(soundUri)
            .setChannelId(DEFAULT.DEFAULT_NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
        notificationManager.notify(DEFAULT.DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    fun cancelNotifyWithDefaultStyle(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(DEFAULT.DEFAULT_NOTIFICATION_ID)
        }
    }

    // For Default Style with Buttons as Action with maximum 3 with default style else in media it vary  Notification

    fun notifyWithDefaultStyleAndAction(
        context: Context,
        title: String? = null,
        message: String? = null,
        @DrawableRes notificationSmallIcon: Int,
        listOfActions: ArrayList<NotificationAction>
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_ID,
                ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.description =
                ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, attributes)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder = NotificationCompat.Builder(
            context,
            ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_CHANNEL_ID
        )

        notificationBuilder
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(message)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(notificationSmallIcon) // Small Notification  image
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setSound(soundUri)

        for (action in listOfActions) {
            notificationBuilder.addAction(
                action.actionImage,
                action.actionname,
                action.notificatoinPendingIntent
            )
        }

        notificationManager.notify(
            ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    fun cancelNotifyWithDefaultStyleAndAction(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(ACTION.DEFAULT_WITH_ACTION_NOTIFICATION_ID)
        }
    }

    // For Default Style with Big Text as Conent

    fun notifyWithBigContent(
        context: Context,
        title: String? = null,
        message: String? = null,
        @DrawableRes notificationSmallIcon: Int,
        largeIcon: Bitmap? = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_app_notification_blue
        ),
        bigContentTitle: String? = null,
        bigExpandedContent: String? = null,
        bigCollapsedContent: String? = null,
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_ID,
                BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.description =
                BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, attributes)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder = NotificationCompat.Builder(
            context,
            BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_ID
        )

        notificationBuilder
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeIcon)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(bigExpandedContent)
                    .setBigContentTitle(bigContentTitle)
                    .setSummaryText(bigCollapsedContent) // in gmail notification this is be the email address
            )
            .setChannelId(BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_CHANNEL_ID)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(notificationSmallIcon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setSound(soundUri)

        notificationManager.notify(
            BIG_CONTENT.DEFAULT_WITH_BIG_CONTENT_NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    fun cancelNotifyWithBigContent(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(DEFAULT.DEFAULT_NOTIFICATION_ID)
        }
    }

    // For Default Style with Big Picture as Conent

    fun notifyWithBigPicture(
        context: Context,
        title: String? = null,
        message: String? = null,
        @DrawableRes notificationSmallIcon: Int,
        largeIcon: Bitmap? = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_back
        ),
        bigPictureAsContent: Bitmap? = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_back
        ),
        hideExpandedLargeIcon: Boolean
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_ID,
                BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.description =
                BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, attributes)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder = NotificationCompat.Builder(
            context,
            BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_ID
        )
        notificationBuilder
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeIcon)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bigPictureAsContent)
                    .bigLargeIcon(if (hideExpandedLargeIcon) null else largeIcon)
            ) // .bigLargeIcon(null) to hide pic when expand notification and all pic show
            .setWhen(System.currentTimeMillis())
            .setChannelId(BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(notificationSmallIcon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setSound(soundUri)

        notificationManager.notify(
            BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    fun cancelNotifyWithBigPicture(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(BIG_IMAGE_CONTENT.DEFAULT_WITH_BIG_IMAGE_CONTENT_NOTIFICATION_ID)
        }
    }

    // For Media Style

    fun notifyWithMedia(
        context: Context,
        track: Track,
        subText: String? = null,
        artWork: Bitmap? =BitmapFactory.decodeResource(
            context.resources,
            R.drawable.t1
        ),
        listOfActions: ArrayList<NotificationAction>,
        mediaSessionCompat: MediaSessionCompat
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                MEDIA.MUSIC_NOTIFICATION_CHANNEL_ID,
                MEDIA.MUSIC_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = MEDIA.MUSIC_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.setSound(null, null)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder =
            NotificationCompat.Builder(context, MEDIA.MUSIC_NOTIFICATION_CHANNEL_ID)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mediaSessionCompat.setMetadata(
                MediaMetadataCompat.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, track.title)
                    .putString(MediaMetadata.METADATA_KEY_ARTIST, track.artist)
                    .build()
            )
        }
        notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setContentTitle(track.title)
            .setContentText(track.artist)
            .setLargeIcon(artWork)
            .setOnlyAlertOnce(true)
            .setChannelId(MEDIA.MUSIC_NOTIFICATION_CHANNEL_ID)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1, 2, 3)
                    .setMediaSession(mediaSessionCompat.sessionToken)
            )
            .setSubText(subText)
            .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
            .priority = NotificationCompat.PRIORITY_HIGH

        for (action in listOfActions) {
            notificationBuilder.addAction(
                action.actionImage,
                action.actionname,
                action.notificatoinPendingIntent
            )
        }

        notificationManager.notify(MEDIA.MUSIC_NOTIFICATION_ID, notificationBuilder.build())
    }

    fun cancelNotifyWithMedia(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(MEDIA.MUSIC_NOTIFICATION_ID)
        }
    }

    // For Messages

      /*                     How to get Data from Remote Input ?
              val remoteInput: Bundle = RemoteInput.getResultsFromIntent(intent)
              if (remoteInput != null) {
                  val replyText = remoteInput.getString("key_text_reply")
                  val answer = Message(replyText!!, "you")
                  MainActivity.MESSAGES.add(answer)
                  MainActivity.sendMessage(context!!)
              }
           */

    fun notifyWithReplyMessage(
        context: Context,
        @DrawableRes replyIcon: Int,
        replyTitle: String,
        replyPendingIntent: PendingIntent? = null,
        remoteInput: RemoteInput? = RemoteInput.Builder("key_text_reply")
            .setLabel("Your answer...")
            .build(),
        conversationType: String? = "Comments",
        messages: ArrayList<MessageContent>
    ) {

        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
            replyIcon,
            replyTitle,
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()

        val me: Person = Person.Builder().setName("you").build()

        val messagingStyle = NotificationCompat.MessagingStyle(me)
        messagingStyle.conversationTitle = conversationType
        for (chatMessage in messages) {
            val sender: Person =
                Person.Builder().setName("${chatMessage.messangerName}").build()

            val notificationMessage = NotificationCompat.MessagingStyle.Message(
                chatMessage.messageContent,
                chatMessage.timeStamp,
                sender
            )
            messagingStyle.addMessage(notificationMessage)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHAT.CHAT_NOTIFICATION_CHANNEL_ID,
                CHAT.CHAT_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.description = CHAT.CHAT_NOTIFICATION_CHANNEL_DESCIBTION
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, attributes)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder =
            NotificationCompat.Builder(context, CHAT.CHAT_NOTIFICATION_CHANNEL_ID)


        notificationBuilder.setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setStyle(messagingStyle)
            .addAction(replyAction)
            .setColor(Color.BLUE)
            .setChannelId(CHAT.CHAT_NOTIFICATION_CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setOnlyAlertOnce(true)

        notificationManager.notify(CHAT.CHAT_NOTIFICATION_ID, notificationBuilder.build())
    }

    fun cancelNotifyWithReplyMessage(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch(Dispatchers.Main) {
            notificationManager.cancel(CHAT.CHAT_NOTIFICATION_ID)
        }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: NotificationUtil? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            NotificationUtil(context).also {
                instance = it
            }
        }
    }

}