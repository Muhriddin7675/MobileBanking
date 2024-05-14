package com.example.mobilebanking.presentantion.screen.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.data.MarkerData
import com.example.mobilebanking.presentantion.dialog.MapInfoBottomDialog
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import org.orbitmvi.orbit.compose.collectSideEffect

class MapScreen : Screen {
    @Composable
    override fun Content() {

        val model: MapContract.Model = getViewModel<MapModule>()

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        model.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is MapContract.SideEffect.OpenMapBottomDialog -> {
                    bottomSheetNavigator.show(
                        MapInfoBottomDialog(
                            markerData = sideEffect.data,
                            clickCancel = {
                                bottomSheetNavigator.hide()
                            }
                        )
                    )
                }
            }
        }

        MobileBankingTheme {
            MapContent(model::onEventDispatcher)
        }
    }

    @Composable
    fun MapContent(
        onEventDispatcher: (MapContract.Intent) -> Unit
    ) {
        val ls = listOf(
            MarkerData(
                lat = 41.31904027624511, 69.24142262507962,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "\"OOO\"UZPAYNET",
                titleBankInfo = "Kompaniya ofisi",
                textLocation = "Furqat ko'chasi 10, 100021, Тоshkent, Toshkent, Узбекистан",
            ),
            MarkerData(
                lat = 41.32589485189752, 69.27586774488323,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Abdulla Qodiriy ko'chasi 36, Тоshkent, Toshkent, Узбекистан",
            ),
            MarkerData(
                lat = 41.294826809256286, 69.19171528342507,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Mobil telefonni zaryadlash mashinasi",
                textLocation = "75RV+M3P, 100173, Tashkent, Toshkent Shahri, Узбекистан",
            ),

            MarkerData(
                lat = 41.28830469126177, 69.18416710804408,
                image = R.drawable.image_uzmobil_paynet,
                titleBank = "Uzmobile-Paynet",
                titleBankInfo = "Kompaniya ofisi",
                textLocation = "75PP+997 Фархадский рынок, 100123, Tashkent, Toshkent Shahri, Узбекистан",
            ),
            MarkerData(
                lat = 41.31904027624511, 69.24142262507962,
                image = R.drawable.image_llc_uzpaynet,
                titleBank = "\"UZPAYNET\" LLC | Платежная система",
                titleBankInfo = "Paynet",
                textLocation = "Muqimiy ko'chasi 59, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.273248795870245, 69.27772122327724,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet - Uzmobile - UMS",
                titleBankInfo = "Telefon kompaniyasi",
                textLocation = "Geydar Aliev ko'chasi 202, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.32049735685445, 69.29397531880431,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Kari Niyazov ko'chasi 10, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.32505529592058, 69.30320042044544,
                image = R.drawable.image_paynet_market,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Oqqo’rg’on ko'chasi 14, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.34811357966962, 69.34592720699375,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "PAYNET, КОМПЬЮТЕРНЫЕ УСЛУГИ, АВТОСТРАХОВАНИЕ",
                titleBankInfo = "Kompaniya,offisi",
                textLocation = "88WW+R59, Trotuar, Tashkent, Toshkent Shahri, Узбекистан",
                textTelephoneNumber = "+998 99 877 11 25"
            ),
            MarkerData(
                lat = 41.320519559921834, 69.2966280351533,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Пейнет",
                titleBankInfo = "To'lov punkti - pullik yo'lda sayohat uchun to'lov.",
                textLocation = "879W+QH9, Tashkent, Toshkent Shahri, Узбекистан",
                textTelephoneNumber = "+998 99 877 11 25"
            ),
            )

//        ModalBottomSheet(
//            onDismissRequest = {
//
//            },
//
//        ) {
//
//        }
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = CameraPositionState(
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(
                            41.31,
                            69.31
                        ), 13f
                    )
                ),
            ) {

                ls.forEach { markerData ->
                    Marker(
                        state = MarkerState(position = LatLng(markerData.lat, markerData.lng)),
                        onClick = {
                            onEventDispatcher.invoke(MapContract.Intent.OpenMapDialog(markerData = markerData))
                            return@Marker true
                        }
                    )
                }


            }
        }
    }
}