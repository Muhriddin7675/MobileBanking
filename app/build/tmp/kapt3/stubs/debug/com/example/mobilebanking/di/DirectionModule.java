package com.example.mobilebanking.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u000bH\'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u000eH\'J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0011H\'J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0014H\'J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0017H\'J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u001aH\'J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0004\u001a\u00020\u001dH\'J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020 H\'\u00a8\u0006!"}, d2 = {"Lcom/example/mobilebanking/di/DirectionModule;", "", "confirmDirection", "Lcom/example/mobilebanking/presentantion/screen/comfirmation/ConfirmDirection;", "impl", "Lcom/example/mobilebanking/presentantion/screen/comfirmation/ConfirmDirectionImpl;", "historyPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/history/HistoryDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/history/HistoryDirectionImpl;", "homePageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/home/HomeDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/home/HomeDirectionImpl;", "mainScreenDirection", "Lcom/example/mobilebanking/presentantion/screen/main/MainDirection;", "Lcom/example/mobilebanking/presentantion/screen/main/MainDirectionImpl;", "paymentPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/payment/PaymentDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/payment/PaymentDirectionImpl;", "pinCheckDirection", "Lcom/example/mobilebanking/presentantion/screen/pincode/PinCheckDirection;", "Lcom/example/mobilebanking/presentantion/screen/pincode/PinCheckDirectionImpl;", "profilDirection", "Lcom/example/mobilebanking/presentantion/screen/profil/ProfilDirection;", "Lcom/example/mobilebanking/presentantion/screen/profil/ProfilDirectionImpl;", "registerDirection", "Lcom/example/mobilebanking/presentantion/screen/login/RegisterDirection;", "Lcom/example/mobilebanking/presentantion/screen/login/RegisterDirectionImpl;", "splashDirection", "Lcom/example/mobilebanking/presentantion/screen/splash/SplashDirection;", "Lcom/example/mobilebanking/presentantion/screen/splash/SplashDirectionImpl;", "transferPageDirection", "Lcom/example/mobilebanking/presentantion/screen/pager/transfer/TransferDirection;", "Lcom/example/mobilebanking/presentantion/screen/pager/transfer/TransferDirectionImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract interface DirectionModule {
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.splash.SplashDirection splashDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.splash.SplashDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.login.RegisterDirection registerDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.login.RegisterDirectionImpl impl);
    
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
    public abstract com.example.mobilebanking.presentantion.screen.main.MainDirection mainScreenDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.main.MainDirectionImpl impl);
    
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
    public abstract com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirection transferPageDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.pager.transfer.TransferDirectionImpl impl);
    
    @dagger.Binds
    @org.jetbrains.annotations.NotNull
    public abstract com.example.mobilebanking.presentantion.screen.profil.ProfilDirection <profilDirection(@org.jetbrains.annotations.NotNull
    com.example.mobilebanking.presentantion.screen.profil.ProfilDirectionImpl impl);
}