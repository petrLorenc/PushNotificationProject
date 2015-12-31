package cz.united121.notifit.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.Locale;

import cz.united121.notifit.Network.PostAsync;
import cz.united121.notifit.Notifit;
import cz.united121.notifit.R;
import cz.united121.notifit.RegisterPushObject;

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
		Intent registrationComplete = new Intent(Notifit.REGISTRATION_COMPLETE);
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
		// Add custom implementation, as needed.

//		NetworkInterface client = ServiceGenerator.createService(NetworkInterface.class);
//
//		String _OSVERSION = System.getProperty("os.version");
//		String _RELEASE = Build.VERSION.RELEASE;
//		String _DEVICE = Build.DEVICE;
//		String _MODEL = Build.MODEL;
//		String _PRODUCT = Build.PRODUCT;
//		String _BRAND = Build.BRAND;
//		String _DISPLAY = Build.DISPLAY;
//		String _CPU_ABI = Build.CPU_ABI;
//		String _CPU_ABI2 = Build.CPU_ABI2;
//		String _UNKNOWN = Build.UNKNOWN;
//		String _HARDWARE = Build.HARDWARE;
//		String _ID = Build.ID;
//		String _MANUFACTURER = Build.MANUFACTURER;
//		String _SERIAL = Build.SERIAL;
//		String _USER = Build.USER;
//		String _HOST = Build.HOST;
//
//
		// Fetch and print a list of the contributors to this library.

		RegisterPushObject registerPushObject = new RegisterPushObject(
				Notifit.PROJECT_TOKEN,
				Notifit.APP_TOKEN,
				token,
				Build.VERSION.SDK_INT + "",
				Build.DEVICE,
				Build.MODEL,
				Build.PRODUCT,
				Locale.getDefault().getDisplayLanguage()
		);
		new PostAsync(registerPushObject).execute();

	}
}
