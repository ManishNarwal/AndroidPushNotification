package com.example.androidpushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.IOException;
import java.net.URL;

//
public class FireMsgService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);

        Log.d("Msg", "Message received ["+remoteMessage+"]");
        Log.d("Msg", "Body!!!!!!!! received ["+remoteMessage.getData().get("image")+"]");

        // Create Notification
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap image = null;
        try {
            URL url = new URL(remoteMessage.getData().get("image"));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_stat_name).setContentTitle(remoteMessage.getData().get("title")).setContentText(remoteMessage.getData().get("body")).setAutoCancel(true).setLargeIcon(image).setSubText("Incoming Client")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("title"))).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image).bigLargeIcon(image))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentIntent(pendingIntent);

 // notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1410, notificationBuilder.build());
    }
}
