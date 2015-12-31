package cz.united121.pushnotificationproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {11/11/2015}
 **/
public class MyInstanceIDListenerService extends InstanceIDListenerService {
	public static final String TAG = MyInstanceIDListenerService.class.getName();

	/**
	 * Called if InstanceID token is updated. This may occur if the security of
	 * the previous token had been compromised. This call is initiated by the
	 * InstanceID provider.
	 */
	@Override
	public void onTokenRefresh() {
		Log.d(TAG, "onTokenRefresh() called with: " + "");
		// Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
		Intent intent = new Intent(this, RegistrationIntentService.class);
		startService(intent);
	}
}
