package com.example.mobilebanking.di

import com.example.mobilebanking.domain.AppRepository
import com.example.mobilebanking.domain.CardRepository
import com.example.mobilebanking.domain.impl.AppRepositoryImpl
import com.example.mobilebanking.domain.impl.CardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {
    @Binds
    fun getRepo(impl: AppRepositoryImpl): AppRepository

    @Binds
    fun getCardRepo(impl:CardRepositoryImpl): CardRepository


}