package cz.united121.pushnotificationproject;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/16/2015}
 **/
public class RegisterPushObject {
	public static final String TAG = RegisterPushObject.class.getName();

	public String ProjectToken;
	public String AppToken;
	public String DeviceToken;
	public String DeviceApiLevel;
	public String DeviceLanguage;
	public String DeviceModel;
	public String DeviceOperatingSystemVersion;
	public String DeviceProduct; //Locale.getDefault().getDisplayLanguage();

	public RegisterPushObject() {
	}

	public RegisterPushObject(String deviceToken, String osVersion, String apiLevel, String device, String model, String product, String language) {
		ProjectToken = "5a77c122-099a-e511-9426-00155d000710";
		AppToken = "ee854de0-95ad-e511-9427-00155d000710";
		DeviceToken = deviceToken;
		DeviceApiLevel = apiLevel;
		DeviceOperatingSystemVersion = device;
		DeviceModel = model;
		DeviceProduct = product;
		DeviceLanguage = language;
	}
}
