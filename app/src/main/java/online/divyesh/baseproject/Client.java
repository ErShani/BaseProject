package online.divyesh.baseproject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 26/12/17.
 */

public class Client {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).readTimeout(10, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://unsplash.it")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

}
