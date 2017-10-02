package com.example.ilhamelmujib.aplikasinotifikasi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private String NOTIFICATION_TITLE = "Notification Sample App";
    private String CONTENT_TEXT = "Expand me to see a detailed message!";
    EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDefault = (Button) findViewById(R.id.btnDefault);
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifDefault();
            }
        });

        Button btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifAction();
            }
        });

        Button btnFixed = (Button) findViewById(R.id.btnFixed);
        btnFixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifFixed();
            }
        });

        Button btnCustom = (Button) findViewById(R.id.btnCustom);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifCustom();
            }
        });
    }

    public void notifDefault() {
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new Notification.Builder(this)
                .setContentTitle("Your Battery is Low")
                .setContentText("Turn off.")
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);

    }

    public void notifAction() {
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new Notification.Builder(this)
                .setContentTitle("M. Ilham Abdul Mujib")
                .setContentText("3 Likes")
                .setSmallIcon(R.drawable.ic_favorite)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_call, "Call", pIntent)
                .addAction(R.drawable.ic_chat, "Chat", pIntent)
                .addAction(R.drawable.ic_all_inclusive, "More", pIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);

    }

    public void notifFixed() {
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new Notification.Builder(this)
                .setContentTitle("Tuyul Messengger")
                .setContentText("Kuota habis")
                .setSmallIcon(R.drawable.ic_swap)
                .setContentIntent(pIntent)
                .setOngoing(true)
                .setAutoCancel(false)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);

    }

    public void notifCustom() {
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.view_expanded_notification);
        Intent leftIntent = new Intent(this, TestActivity.class);

        leftIntent.setAction("left");
        expandedView.setOnClickPendingIntent(R.id.left_button, PendingIntent.getService(this, 0, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        Intent rightIntent = new Intent(this, TestActivity.class);
        rightIntent.setAction("right");
        expandedView.setOnClickPendingIntent(R.id.right_button, PendingIntent.getService(this, 1, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout.view_collapsed_notification);
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_print)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(CONTENT_TEXT)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

        NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
