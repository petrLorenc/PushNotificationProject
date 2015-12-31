package cz.united121.pushnotificationproject;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * TODO add class description
 * Created by Petr Lorenc[Lorenc55Petr@seznam.cz] on {12/16/2015}
 **/
public class ServiceGenerator {

	private static OkHttpClient httpClient = new OkHttpClient();
	private static Retrofit.Builder builder =
			new Retrofit.Builder()
					.baseUrl(NetworkInterface.URL)
					.addConverterFactory(GsonConverterFactory.create());

	public static <S> S createService(Class<S> serviceClass) {
		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(serviceClass);
	}
}
