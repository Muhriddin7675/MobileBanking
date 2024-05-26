package com.example.mobilebanking.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u000bH\'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u000eH\'J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0011H\'J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0014H\'J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0017H\'J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u001aH\'J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0004\u001a\u00020\u001dH\'J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020 H\'J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0004\u001a\u00020#H\'J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020&H\'J\u0010\u0010\'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020)H\'J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u0004\u001a\u00020,H\'J\u0010\u0010-\u001a\u00020.2\u0006\u0010\u0004\u001a\u00020/H\'J\u0010\u00100\u001a\u0002012\u0006\u0010\u0004\u001a\u000202H\'J\u0010\u00103\u001a\u0002042\u0006\u0010\u0004\u001a\u000205H\'J\u0010\u00106\u001a\u0002072\u0006\u0010\u0004\u001a\u000208H\'J\u0010\u00109\u001a\u00020:2\u0006\u0010\u0004\u001a\u00020;H\'J\u0010\u0010<\u001a\u00020=2\u0006\u0010\u0004\u001a\u00020>H\'J\u0010\u0010?\u001a\u00020@2\u0006\u0010\u0004\u001a\u00020AH\'J\u0010\u0010B\u001a\u00020C2\u0006\u0010\u0004\u001a\u00020DH\'J\u0010\u0010E\u001a\u00020F2\u0006\u0010\u0004\u001a\u00020GH\'J\u0010\u0010H\u001a\u00020I2\u0006\u0010\u0004\u001a\u00020JH\'J\u0010\u0010K\u001a\u00020L2\u0006\u0010\u0004\u001a\u00020MH\'\u00a8\u0006N"}, d2 = {"Lcom/example/mobilebanking/di/DirectionModule;", "", "addCardDirection", "Lcom/example/mobilebanking/presentantion/screen/addcard/AddCardDirection;", "impl", "Lcom/example/mobilebanking/presentantion/screen/addcard/AddCardDirectionImpl;", "allCardDirection", "Lcom/example/mobilebanking/presentantion/screen/allcard/AllCardDirection;", "Lcom/example/mobilebanking/presentantion/screen/allcard/AllCardDirectionImpl;", "appSettingDirection", "Lcom/example/mobilebanking/presentantion/screen/setting/AppSettingDirection;", "Lcom/example/mobilebanking/presentantion/screen/setting/AppSettingDirectionImpl;", "cardDetailDirection", "Lcom/example/mobilebanking/presentantion/screen/card_details/CardDetailsDirections;", "Lcom/example/mobilebanking/presentantion/screen/card_details/CardDetailsDirectionsImp;", "cardThemeDirection", "Lcom/example/mobilebanking/presentantion/screen/card_theme/CardThemeDirections;", "Lcom/example/mobilebanking/presentantion/screen/card_theme/CardThemeDirectionsImp;", "conditionsDirection", "Lcom/example/mobilebanking/presentantion/screen/coditions/ConditionsDirection;", "Lcom/example/mobilebanking/presentantion/screen/coditions/ConditionsDirectionImpl;", "confirmDirection", "Lcom/example/mobilebanking/presentantion/screen/comfirmation/ConfirmDirection;", "Lcom/example/mobilebanking/presentantion/screen/comfirmation/ConfirmDirectionImpl;", "dataEntryDirection", "Lcom/example/mobilebanking/presentantion/screen/dataentry/DataEntryDirection;", "Lcom/example/mobilebanking/presentantion/screen/dataentry/DataEntryDirectionImpl;", "historyPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/history/HistoryDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/history/HistoryDirectionImpl;", "homePageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/home/HomeDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/home/HomeDirectionImpl;", "identityVerificationDirection", "Lcom/example/mobilebanking/presentantion/screen/identity/IdentityVerificationDirection;", "Lcom/example/mobilebanking/presentantion/screen/identity/IdentityVerificationDirectionImpl;", "mapDirection", "Lcom/example/mobilebanking/presentantion/screen/map/MapDirection;", "Lcom/example/mobilebanking/presentantion/screen/map/MapDirectionImpl;", "paymentCardDirection", "Lcom/example/mobilebanking/presentantion/screen/paymentcard/PaymentCardDirection;", "Lcom/example/mobilebanking/presentantion/screen/paymentcard/PaymentCardDirectionImpl;", "paymentPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/payment/PaymentDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/payment/PaymentDirectionImpl;", "pinCheckDirection", "Lcom/example/mobilebanking/presentantion/screen/pincode/PinCheckDirection;", "Lcom/example/mobilebanking/presentantion/screen/pincode/PinCheckDirectionImpl;", "pinScreenDirection", "Lcom/example/mobilebanking/presentantion/screen/pincheck/PinDirection;", "Lcom/example/mobilebanking/presentantion/screen/pincheck/PinDirectionImpl;", "profilDirection", "Lcom/example/mobilebanking/presentantion/screen/profil/ProfilDirection;", "Lcom/example/mobilebanking/presentantion/screen/profil/ProfilDirectionImpl;", "registerDirection", "Lcom/example/mobilebanking/presentantion/screen/register/RegisterDirection;", "Lcom/example/mobilebanking/presentantion/screen/register/RegisterDirectionImpl;", "splashDirection", "Lcom/example/mobilebanking/presentantion/screen/splash/SplashDirection;", "Lcom/example/mobilebanking/presentantion/screen/splash/SplashDirectionImpl;", "transferCardDirections", "Lcom/example/mobilebanking/presentantion/screen/transfer_card/TransferCardDirections;", "Lcom/example/mobilebanking/presentantion/screen/transfer_card/TransferCardDirectionsImp;", "transferMyCardDirections", "Lcom/example/mobilebanking/presentantion/screen/to_mycard/TransferMyCardDirections;", "Lcom/example/mobilebanking/presentantion/screen/to_mycard/TransferMyCardDirectionsImp;", "transferPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/transfer/TransferDirections;", "Lcom/example/mobilebanking/presentantion/screen/pager/transfer/TransferDirectionsImp;", "transferSuccessDirections", "Lcom/example/mobilebanking/presentantion/screen/transfer_success/TransferSuccessDirections;", "Lcom/example/mobilebanking/presentantion/screen/transfer_success/TransferSuccessDirectionsImp;", "transferVerifyDirections", "Lcom/example/mobilebanking/presentantion/screen/transfer_verify/TransferVerifyDirections;", "Lcom/example/mobilebanking/presentantion/screen/transfer_verify/TransferVerifyDirectionsImp;", "whatIsDirection", "Lcom/example/mobilebanking/presentantion/screen/whatis/WhatIsPaymentCardDirection;", "Lcom/example/mobilebanking/presentantion/screen/whatis/WhatIsPaymentCardDirectionImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract interface DirectionModule {
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.splash.SplashDirection splashDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.splash.SplashDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.register.RegisterDirection registerDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.register.RegisterDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirection confirmDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirection pinCheckDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pincode.PinCheckDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pager.home.HomeDirection homePageDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pager.home.HomeDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirection paymentPageDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pager.payment.PaymentDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirection historyPageDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pager.history.HistoryDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirections transferPageDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.profil.ProfilDirection profilDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.profil.ProfilDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.map.MapDirection mapDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.map.MapDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.addcard.AddCardDirection addCardDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.addcard.AddCardDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.pincheck.PinDirection pinScreenDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pincheck.PinDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.setting.AppSettingDirection appSettingDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.setting.AppSettingDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.paymentcard.PaymentCardDirection paymentCardDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.paymentcard.PaymentCardDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.whatis.WhatIsPaymentCardDirection whatIsDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.whatis.WhatIsPaymentCardDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.coditions.ConditionsDirection conditionsDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.coditions.ConditionsDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationDirection identityVerificationDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.dataentry.DataEntryDirection dataEntryDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.dataentry.DataEntryDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.card_details.CardDetailsDirections cardDetailDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.card_details.CardDetailsDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.card_theme.CardThemeDirections cardThemeDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.card_theme.CardThemeDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.allcard.AllCardDirection allCardDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.allcard.AllCardDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.transfer_card.TransferCardDirections transferCardDirections(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.transfer_card.TransferCardDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.transfer_success.TransferSuccessDirections transferSuccessDirections(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.transfer_success.TransferSuccessDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.transfer_verify.TransferVerifyDirections transferVerifyDirections(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.transfer_verify.TransferVerifyDirectionsImp impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.to_mycard.TransferMyCardDirections transferMyCardDirections(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.to_mycard.TransferMyCardDirectionsImp impl);
}