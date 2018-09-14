package com.furianrt.itunesmusicapp.di.application;

import android.app.Application;
import android.content.Context;

import com.furianrt.itunesmusicapp.data.DataManager;
import com.furianrt.itunesmusicapp.data.DataManagerImp;
import com.furianrt.itunesmusicapp.data.api.ITunesApiService;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application application) {
        this.mApp = application;
    }

    @Provides
    @AppScope
    Context provideContext() {
        return mApp;
    }

    @Provides
    @AppScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.e(message));
        return logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @AppScope
    Interceptor provideParamInterceptor() {
        return chain -> {
            Request original = chain.request();

            HttpUrl.Builder urlBuilder = original.url().newBuilder();

            List<String> urlPathSegments = original.url().pathSegments();

            if (urlPathSegments.contains("lookup")) {
                urlBuilder.addQueryParameter("entity", "song");
            } else if (urlPathSegments.contains("search")) {
                urlBuilder.addQueryParameter("entity", "album");
            }

            HttpUrl urlWithParams = urlBuilder.build();

            Request.Builder requestBuilder = original.newBuilder()
                    .method(original.method(), original.body())
                    .url(urlWithParams);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                     Interceptor paramInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(paramInterceptor)
                .build();
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://itunes.apple.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    ITunesApiService provideITunesApiService(Retrofit retrofit) {
        return retrofit.create(ITunesApiService.class);
    }

    @Provides
    @AppScope
    DataManager provideDataManager(ITunesApiService apiService) {
        return new DataManagerImp(apiService);
    }
}
