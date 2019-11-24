package blue_bay.app.di.module

import blue_bay.app.data.api.AppApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import blue_bay.app.data.api.AuthApi
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import blue_bay.app.data.contracts.RemoteContract

@Module
class ApiModule {
    @Provides @Singleton fun provideGson() =
            GsonBuilder()
                .setLenient()
                .create()

    @Provides
    @Singleton
    @Named("AppOkHttp")
    fun provideAppOkHttpClient(): OkHttpClient {
        return   OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Named("AppRetrofit")
    fun provideAppRetrofit(gson: Gson, @Named("AppOkHttp") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteContract.API_APP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides @Singleton fun provideAppApi(@Named("AppRetrofit") retrofit: Retrofit): AppApi =
        retrofit.create(AppApi::class.java)

    @Provides
    @Singleton
    @Named("AuthRetrofit")
    fun provideAuthRetrofit(gson: Gson, @Named("AppOkHttp") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteContract.API_AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides @Singleton fun provideAuthApi(@Named("AuthRetrofit") retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)
}