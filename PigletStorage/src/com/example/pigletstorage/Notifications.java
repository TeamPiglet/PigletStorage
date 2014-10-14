package com.example.pigletstorage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;

public class Notifications {
	
	public void createAddedNewItemNotification(Context context, Bitmap largeIcon, String name, String price, String type, String quantity) {
		Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setLargeIcon(Bitmap.createScaledBitmap(largeIcon, 256, 256, false))
                        .setContentTitle("Added new Product to DB")
                        .setContentText(name);

        Notification.InboxStyle inboxStyle =
                new Notification.InboxStyle();
        
        inboxStyle.setBigContentTitle("Added new Product:"); 
        inboxStyle.addLine("Name: " + name);
        inboxStyle.addLine("Type: " + type);
        inboxStyle.addLine("Price: " + price);
        inboxStyle.addLine("Quantity: " + quantity);
        
        builder.setStyle(inboxStyle);
        
        Notification notification = builder.build();

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(R.id.simple_notification_id, notification);
    }
}
