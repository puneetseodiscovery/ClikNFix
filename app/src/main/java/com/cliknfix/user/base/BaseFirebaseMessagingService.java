package com.cliknfix.user.base;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.loginSignup.LoginSignupActivity;
import com.cliknfix.user.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.user.util.PreferenceHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class BaseFirebaseMessagingService extends FirebaseMessagingService {
    String deviceToken;


    public BaseFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate","Working");
        FirebaseInstanceId.getInstance().getInstanceId()
        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "getInstanceId failed", task.getException());
                    return;
                }
                // Get new Instance ID token
                deviceToken = task.getResult().getToken();
                Log.e("Firebase Token", deviceToken);
                new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, deviceToken);
                String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
                Log.d("1mLoginToken", mLoginToken);
           /* if(mLoginToken!= null)
                sendNotification();*/
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel=new NotificationChannel("my_notification","n_channel",NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("description");
            notificationChannel.setName("Channel Name");
            //notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("Data","" +remoteMessage.getData());
        ArrayMap data = (ArrayMap) remoteMessage.getData();
        Intent intent = new Intent(this, TechnicianDetailActivity.class);

        //int s = (int) data.get("rating");
        intent.putExtra("rating",(String) data.get("rating"));
        intent.putExtra("user_id",(String) data.get("user_id"));
        intent.putExtra("technician_name",(String) data.get("technician_name"));
        intent.putExtra("labour_rate",(String) data.get("labour_rate"));
        intent.putExtra("message",(String) data.get("message"));
        intent.putExtra("technician_id",(String) data.get("technician_id"));
        startActivity(intent);

        //dsendNotification();

    }

    @Override
    public void onNewToken(String token) {
        Log.e("onNewToken","Working");
        this.deviceToken = token;
        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, token);
        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        Log.d(TAG, "Refreshed token: " + mLoginToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    private void sendNotification(){
        Log.e("sendNotification","Working");

       /* NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);
*/
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle("Notification Title")
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentText("Content goes here")
                .setContentIntent(pendingIntent)
                //.setStyle(style)
                //.setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);


        notificationManager.notify(1, notificationBuilder.build());


    }
}
