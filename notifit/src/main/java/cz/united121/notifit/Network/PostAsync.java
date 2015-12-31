package cz.united121.notifit.Network;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.united121.notifit.RegisterPushObject;

/**
 * Take from http://danielnugent.blogspot.cz/2015/06/updated-jsonparser-with.html
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/29/2015}
 **/
public class PostAsync extends AsyncTask<Void, String, JSONObject> {
	public static final String TAG = PostAsync.class.getName();
	JSONParser jsonParser = new JSONParser();

	private static final String LOGIN_URL = "http://notifit.io/api/DeviceAndroid";

	RegisterPushObject mRegisterPushObject;

	public PostAsync(RegisterPushObject registerPushObject) {
		mRegisterPushObject = registerPushObject;
	}

	@Override
	protected JSONObject doInBackground(Void... args) {

		try {
			JSONObject json = jsonParser.makeHttpRequest(
					LOGIN_URL, "POST", mRegisterPushObject);

			if (json != null) {
				Log.d("JSON result", json.toString());

				return json;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(JSONObject json) {
		Log.d(TAG, "onPostExecute() called with: " + "json = [" + json + "]");
	}

}