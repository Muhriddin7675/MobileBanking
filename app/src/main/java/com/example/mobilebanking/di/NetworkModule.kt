package com.example.mobilebanking.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.remote.api.AddCardApi
import com.example.mobilebanking.util.TokenAuthenticator
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ActivityComponent::class)
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

}
