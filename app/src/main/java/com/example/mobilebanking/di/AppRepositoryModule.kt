package com.example.mobilebanking.di

import com.example.mobilebanking.domain.repositori.AppRepository
import com.example.mobilebanking.domain.repositori.CardRepository
import com.example.mobilebanking.domain.repositori.LocalRepository
import com.example.mobilebanking.domain.repositori.TransferRepository
import com.example.mobilebanking.domain.repositori.impl.AppRepositoryImpl
import com.example.mobilebanking.domain.repositori.impl.CardRepositoryImpl
import com.example.mobilebanking.domain.repositori.impl.LocalRepositoryImp
import com.example.mobilebanking.domain.repositori.impl.TransferRepositoryImpl
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
    fun getCardRepo(impl: CardRepositoryImpl): CardRepository

    @Binds
    fun getTransferRepo(impl: LocalRepositoryImp): LocalRepository

    @Binds
    fun getLocalRepo(impl: TransferRepositoryImpl): TransferRepository

}