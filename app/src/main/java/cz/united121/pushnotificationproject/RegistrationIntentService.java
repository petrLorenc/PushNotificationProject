package cz.united121.pushnotificationproject;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {11/11/2015}
 **/
public class RegistrationIntentService extends IntentService {
	public static final String TAG = RegistrationIntentService.class.getName();

	public RegistrationIntentService() {
		super(TAG);
	}

	public RegistrationIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent() called with: " + "intent = [" + intent + "]");

		try {

			InstanceID instanceID = InstanceID.getInstance(this);
			String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
					GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
			Log.d(TAG, "GCM Registration Token: " + token);

			sendRegistrationToServer(token);

		} catch (Exception e) {
			Log.d(TAG, "Failed to complete token refresh", e);
		}
		Intent registrationComplete = new Intent(MainActivity.REGISTRATION_COMPLETE);
		LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
	}

	/**
	 * Persist registration to third-party servers.
	 * <p/>
	 * Modify this method to associate the user's GCM registration token with any server-side account
	 * maintained by your application.
	 *
	 * @param token The new token.
	 */
	private void sendRegistrationToServer(String token) {
		Log.d(TAG, "sendRegistrationToServer() called with: " + "token = [" + token + "]");
		// Add custom implementation, as needed.

		NetworkInterface client = ServiceGenerator.createService(NetworkInterface.class);

		String _OSVERSION = System.getProperty("os.version");
		String _RELEASE = android.os.Build.VERSION.RELEASE;
		String _DEVICE = android.os.Build.DEVICE;
		String _MODEL = android.os.Build.MODEL;
		String _PRODUCT = android.os.Build.PRODUCT;
		String _BRAND = android.os.Build.BRAND;
		String _DISPLAY = android.os.Build.DISPLAY;
		String _CPU_ABI = android.os.Build.CPU_ABI;
		String _CPU_ABI2 = android.os.Build.CPU_ABI2;
		String _UNKNOWN = android.os.Build.UNKNOWN;
		String _HARDWARE = android.os.Build.HARDWARE;
		String _ID = android.os.Build.ID;
		String _MANUFACTURER = android.os.Build.MANUFACTURER;
		String _SERIAL = android.os.Build.SERIAL;
		String _USER = android.os.Build.USER;
		String _HOST = android.os.Build.HOST;


		// Fetch and print a list of the contributors to this library.
		try {
			client.sendInformationToServer(new RegisterPushObject(
					token,
					System.getProperty("os.version"),
					Build.VERSION.SDK_INT + "",
					Build.DEVICE,
					Build.MODEL,
					Build.PRODUCT,
					Locale.getDefault().getDisplayLanguage()
			)).execute();
		} catch (IOException e) {
			Log.d(TAG, "IOException");
			e.printStackTrace();
		}
	}
}
