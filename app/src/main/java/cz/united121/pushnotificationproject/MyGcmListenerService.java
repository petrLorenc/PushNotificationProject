package cz.united121.pushnotificationproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {11/11/2015}
 **/
public class MyGcmListenerService extends GcmListenerService {
	public static final String TAG = MyGcmListenerService.class.getName();
	/**
	 * Called when message is received.
	 *
	 * @param from SenderID of the sender.
	 * @param data Data bundle containing message data as key/value pairs.
	 *             For Set of keys use data.keySet().
	 */
	@Override
	public void onMessageReceived(String from, Bundle data) {
		Log.d(TAG, "onMessageReceived() called with: " + "from = [" + from + "], data = [" + data + "]");
		String message = data.getString("message");
		String title = data.getString("title");
		Log.d(TAG, "From: " + from);
		Log.d(TAG, "Message: " + message);

		sendNotification(title, message);
	}

	/**
	 * Create and show a simple notification containing the received GCM message.
	 *
	 * @param message GCM message received.
	 */
	private void sendNotification(String title, String message) {
		Log.d(TAG, "sendNotification() called with: " + "message = [" + message + "]");
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
				PendingIntent.FLAG_ONE_SHOT);

		Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.notifit_logo)
				.setContentTitle(title)
				.setContentText(message)
				.setAutoCancel(true)
				.setSound(defaultSoundUri)
				.setContentIntent(pendingIntent);

		NotificationManager notificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
	}
}
