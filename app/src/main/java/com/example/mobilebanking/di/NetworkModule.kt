package com.example.mobilebanking.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.remote.api.AddCardApi
import com.example.mobilebanking.data.remote.api.UserApi
import com.example.mobilebanking.util.TokenAuthenticator
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun provideGson(): Gson = Gson()



    @[Provides Singleton]
    fun provideOkHttp(
        @ApplicationContext context: Context,
        sharedPreferenceHelper: MyShared,
        cardApi: Provider<AddCardApi>
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(ChuckerInterceptor(context = context))
            .addInterceptor(TokenAuthenticator(sharedPreferenceHelper, cardApi))
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://195.158.16.140/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @[Provides Singleton]
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): AddCardApi = retrofit.create(AddCardApi::class.java)
}
