package cz.united121.pushnotificationproject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/16/2015}
 **/
public interface NetworkInterface {
	public static final String TAG = NetworkInterface.class.getName();

	public static final String URL = "http://notifit.io";
	public static final String MIDDLE = "/api/";

	@POST(MIDDLE + "DeviceAndroid")
	Call<Object> sendInformationToServer(@Body RegisterPushObject registerPushObject);
}
