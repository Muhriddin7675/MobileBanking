package com.example.mobilebanking.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mobilebanking.data.local.dao.CardDao
import com.example.mobilebanking.data.local.dao.LastTransferUserDao
import com.example.mobilebanking.data.local.db.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @[Provides Singleton]
    fun providePref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("MobilBank", Context.MODE_PRIVATE)


    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDB =
        Room.databaseBuilder(context, AppDB::class.java, "app_db").build()

    @[Provides Singleton]
    fun provideCardDao(appDB: AppDB): CardDao =
        appDB.cardDao()

    @[Provides Singleton]
    fun provideLastTransferUserDao(appDB: AppDB): LastTransferUserDao =
        appDB.usersDao()
}