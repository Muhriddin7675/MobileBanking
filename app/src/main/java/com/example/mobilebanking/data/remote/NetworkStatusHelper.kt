package com.example.mobilebanking.data.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import javax.inject.Inject

class ConnectivityLiveData @Inject constructor(val context: Context) : LiveData<NetworkState>() {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    private var countDownTimer: CountDownTimer? = null

    override fun onActive() {
        super.onActive()
        notifyNetworkStatus()
        when {

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(
                getConnectivityManagerCallback()
            )

            else -> {
                lollipopNetworkAvailableRequest()
            }
        }
    }

    override fun onInactive() {
        // When all observers are gone, remove connectivity callback or un register the receiver.
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun lollipopNetworkAvailableRequest() {
        val builder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(
            builder.build(),
            getConnectivityManagerCallback()
        )
    }

    private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                setNetworkState(NetworkState.CONNECTED_SHOWN)
            }

            override fun onLost(network: Network) {
                setNetworkState(NetworkState.DISCONNECTED)
            }
        }
        return networkCallback
    }

    private fun countOnline() {
        countDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                setNetworkState(NetworkState.CONNECTED_NOT_SHOWN)
            }
        }
        countDownTimer?.start()
    }

    private var networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            notifyNetworkStatus()
        }
    }

    private fun setNetworkState(networkState: NetworkState) {
        countDownTimer?.cancel()
        if (networkState == NetworkState.CONNECTED_SHOWN && value == NetworkState.DISCONNECTED) {
            countOnline()
        }
        if (networkState == NetworkState.CONNECTED_SHOWN && value != NetworkState.DISCONNECTED) {
            postValue(NetworkState.CONNECTED_NOT_SHOWN)
        } else {
            postValue(networkState)
        }
    }

    private fun notifyNetworkStatus() {
        val nw = connectivityManager.activeNetwork ?: run {
            setNetworkState(NetworkState.DISCONNECTED)
            return
        }
        val actNw = connectivityManager.getNetworkCapabilities(nw)
        if (actNw != null && (
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    ) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||  actNw.hasTransport(
                            NetworkCapabilities.TRANSPORT_BLUETOOTH
                            )
                    )
        ) {
            setNetworkState(NetworkState.CONNECTED_SHOWN)
        } else {

            setNetworkState(NetworkState.DISCONNECTED)
        }
    }
}

enum class NetworkState(val isConnected: Boolean, var isShown: Boolean) {

    CONNECTED_SHOWN(true, true),

    CONNECTED_NOT_SHOWN(true, false),

    DISCONNECTED(false, true),

    UNINITIALIZED(false, false);
}
