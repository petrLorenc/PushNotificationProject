package cz.united121.notifit;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

import cz.united121.notifit.GCM.GcmListenerService;
import cz.united121.notifit.GCM.PushLibraryBroadcastReceiver;
import cz.united121.notifit.GCM.RegistrationIntentService;

/**
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class Notifit {
	public static final String TAG = Notifit.class.getName();

	public static final String REGISTRATION_COMPLETE = "registrationComplete";
	public static String APP_TOKEN ;
	public static String PROJECT_TOKEN;

	public static WeakReference<Class<?>> intentToClass;

	public static void register(String appToken, String projectToken, Class<?> tClass, Context context) {
		intentToClass = new WeakReference<Class<?>>(tClass);
		APP_TOKEN = appToken;
		PROJECT_TOKEN = projectToken;

		// Start IntentService to register this application with GCM.
		Intent intent = new Intent(context, RegistrationIntentService.class);
		context.startService(intent);

	}

}
