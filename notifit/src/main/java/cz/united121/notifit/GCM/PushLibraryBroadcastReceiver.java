package cz.united121.notifit.GCM;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmReceiver;

import cz.united121.notifit.BuildConfig;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/30/2015}
 **/
public class PushLibraryBroadcastReceiver extends GcmReceiver {
	public static final String TAG = PushLibraryBroadcastReceiver.class.getName();

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onReceive");
		}

	}

}
