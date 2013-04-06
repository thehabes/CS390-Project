package com.example.testing;

import android.app.NotificationManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
	int notifyID = 0; // A required notification parameter.
	@Override
	public void onReceive(Context context, Intent intent) { 
		// If we were to make multiple notifications, notifyID can be incremented and we would have unique IDs.  Remeber: this stuff only
		// happens when the intent is received.  The intent is only received at the time specified by objCalendar (from Select_Workout_Days.java)
		
			//start alarm make
			Uri notifyNoise = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(context, notifyNoise);
			//end alarm make
		
			
			// start notification make
			NotificationCompat.Builder notificationBuild =
			        new NotificationCompat.Builder(context)
			        .setSmallIcon(R.drawable.icon)
			        .setContentTitle("DO YOUR SHIT")
			        .setContentText("UR SO LAZY")
			        .setAutoCancel(true);
			NotificationManager notifyUser = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			// end notification make
			r.play(); // Plays noise
			notifyUser.notify(notifyID, notificationBuild.build()); // Builds and displays notification
			
	}

}
