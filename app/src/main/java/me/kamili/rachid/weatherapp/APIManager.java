package me.kamili.rachid.weatherapp;

import me.kamili.rachid.weatherapp.model.Weather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIManager {


    private static final String URL = "http://api.openweathermap.org/data/2.5/";
    private static Mc2Service mc2Service;

    public static Mc2Service getApiService() {
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mc2Service = restAdapter.create(Mc2Service.class);

        return mc2Service;
    }

    public interface Mc2Service {

        @GET("forecast")
        Call<Weather> getWeatherInfo(@Query("zip") String zip,
                                     @Query("appid") String appid);
    }

}
