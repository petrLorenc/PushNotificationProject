package cz.united121.pushnotificationproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import cz.united121.notifit.Notifit;

public class MainActivity extends AppCompatActivity {
	public static final String TAG = MainActivity.class.getName();
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	public static final String REGISTRATION_COMPLETE = "registrationComplete";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
		setContentView(R.layout.activity_main);

		if (checkPlayServices()) {
			Log.d(TAG, "checkPlayServices=" + true);
			Notifit.register("ee854de0-95ad-e511-9427-00155d000710","5a77c122-099a-e511-9426-00155d000710",MainActivity.class,this);
		}else{
			Log.d(TAG, "checkPlayServices=" + false);
		}
	}

	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	private boolean checkPlayServices() {
		GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
		int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (apiAvailability.isUserResolvableError(resultCode)) {
				apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
						.show();
			} else {
				Log.d(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}
}
