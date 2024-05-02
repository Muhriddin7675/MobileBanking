package com.example.mobilebanking.di

import com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirection
import com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirectionImpl
import com.example.mobilebanking.presentantion.screen.login.RegisterDirection
import com.example.mobilebanking.presentantion.screen.login.RegisterDirectionImpl
import com.example.mobilebanking.presentantion.screen.main.MainDirection
import com.example.mobilebanking.presentantion.screen.main.MainDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirection
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.home.HomeDirection
import com.example.mobilebanking.presentantion.screen.pager.home.HomeDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirection
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirection
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirectionImpl
import com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirection
import com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirectionImpl
import com.example.mobilebanking.presentantion.screen.profil.ProfilDirection
import com.example.mobilebanking.presentantion.screen.profil.ProfilDirectionImpl
import com.example.mobilebanking.presentantion.screen.splash.SplashDirection
import com.example.mobilebanking.presentantion.screen.splash.SplashDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @Binds
    fun splashDirection(impl: SplashDirectionImpl): SplashDirection

    @Binds
    fun registerDirection(impl: RegisterDirectionImpl): RegisterDirection

    @Binds
    fun confirmDirection(impl: ConfirmDirectionImpl): ConfirmDirection

    @Binds
    fun pinCheckDirection(impl: PinCheckDirectionImpl): PinCheckDirection

    @Binds
    fun homePageDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun mainScreenDirection(impl: MainDirectionImpl): MainDirection

    @Binds
    fun paymentPageDirection(impl: PaymentDirectionImpl): PaymentDirection

    @Binds
    fun historyPageDirection(impl: HistoryDirectionImpl): HistoryDirection

    @Binds
    fun transferPageDirection(impl: TransferDirectionImpl): TransferDirection

    @Binds
    fun profilDirection(impl:ProfilDirectionImpl): ProfilDirection




}