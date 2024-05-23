package com.example.mobilebanking.di

import com.example.mobilebanking.presentantion.screen.addcard.AddCardDirection
import com.example.mobilebanking.presentantion.screen.addcard.AddCardDirectionImpl
import com.example.mobilebanking.presentantion.screen.allcard.AllCardDirection
import com.example.mobilebanking.presentantion.screen.allcard.AllCardDirectionImpl
import com.example.mobilebanking.presentantion.screen.card_details.CardDetailsDirections
import com.example.mobilebanking.presentantion.screen.card_details.CardDetailsDirectionsImp
import com.example.mobilebanking.presentantion.screen.card_theme.CardThemeDirections
import com.example.mobilebanking.presentantion.screen.card_theme.CardThemeDirectionsImp
import com.example.mobilebanking.presentantion.screen.coditions.ConditionsDirection
import com.example.mobilebanking.presentantion.screen.coditions.ConditionsDirectionImpl
import com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirection
import com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirectionImpl
import com.example.mobilebanking.presentantion.screen.dataentry.DataEntryDirection
import com.example.mobilebanking.presentantion.screen.dataentry.DataEntryDirectionImpl
import com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationDirection
import com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationDirectionImpl
import com.example.mobilebanking.presentantion.screen.main.MainDirection
import com.example.mobilebanking.presentantion.screen.main.MainDirectionImpl
import com.example.mobilebanking.presentantion.screen.map.MapDirection
import com.example.mobilebanking.presentantion.screen.map.MapDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirection
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.home.HomeDirection
import com.example.mobilebanking.presentantion.screen.pager.home.HomeDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirection
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirectionImpl
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirection
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirectionImpl
import com.example.mobilebanking.presentantion.screen.paymentcard.PaymentCardDirection
import com.example.mobilebanking.presentantion.screen.paymentcard.PaymentCardDirectionImpl
import com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirection
import com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirectionImpl
import com.example.mobilebanking.presentantion.screen.profil.ProfilDirection
import com.example.mobilebanking.presentantion.screen.profil.ProfilDirectionImpl
import com.example.mobilebanking.presentantion.screen.register.RegisterDirection
import com.example.mobilebanking.presentantion.screen.register.RegisterDirectionImpl
import com.example.mobilebanking.presentantion.screen.setting.AppSettingDirection
import com.example.mobilebanking.presentantion.screen.setting.AppSettingDirectionImpl
import com.example.mobilebanking.presentantion.screen.splash.SplashDirection
import com.example.mobilebanking.presentantion.screen.splash.SplashDirectionImpl
import com.example.mobilebanking.presentantion.screen.whatis.WhatIsPaymentCardDirection
import com.example.mobilebanking.presentantion.screen.whatis.WhatIsPaymentCardDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.presentation.pin.PinDirection
import uz.gita.mobilebanking.presentation.pin.PinDirectionImpl

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
    fun profilDirection(impl: ProfilDirectionImpl): ProfilDirection

    @Binds
    fun mapDirection(impl: MapDirectionImpl): MapDirection

    @Binds
    fun addCardDirection(impl: AddCardDirectionImpl): AddCardDirection

    @Binds
    fun pinScreenDirection(impl: PinDirectionImpl): PinDirection

    @Binds
    fun appSettingDirection(impl: AppSettingDirectionImpl): AppSettingDirection

    @Binds
    fun paymentCardDirection(impl: PaymentCardDirectionImpl): PaymentCardDirection

    @Binds
    fun whatIsDirection(impl: WhatIsPaymentCardDirectionImpl): WhatIsPaymentCardDirection

    @Binds
    fun conditionsDirection(impl: ConditionsDirectionImpl): ConditionsDirection

    @Binds
    fun identityVerificationDirection(impl: IdentityVerificationDirectionImpl): IdentityVerificationDirection

    @Binds
    fun dataEntryDirection(impl: DataEntryDirectionImpl): DataEntryDirection

    @Binds
    fun cardDetailDirection(impl: CardDetailsDirectionsImp): CardDetailsDirections

    @Binds
    fun cardThemeDirection(impl:CardThemeDirectionsImp): CardThemeDirections

    @Binds
    fun allCardDirection(impl: AllCardDirectionImpl): AllCardDirection


}