package com.example.mobilebanking.ui.componnent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapComponent(
    modifier: Modifier,
    zoom: Float,
    ls: List<MarkerData>,
    clickMarkerListener: (MarkerData) -> Unit
) {

    GoogleMap(
        modifier = modifier,
        cameraPositionState = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    41.31,
                    69.31
                ), zoom
            )
        ),
        properties = MapProperties(isMyLocationEnabled = true),
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        ls.forEach { markerData ->
            Marker(
                state = MarkerState(position = LatLng(markerData.lat, markerData.lng)),
                onClick = {
                    clickMarkerListener.invoke(markerData)
                    return@Marker true
                }
            )
        }

    }
}

data class MarkerData(
    val lat: Double,
    val lng: Double,
    val name: String
)

/*

@Composable
fun MapComponent(
    modifier: Modifier,
    courierLocation: Location?,
    orders: List<OrderResponse.DataItem>,
    openFullMapListener: () -> Unit
) {

    Box(modifier = modifier) {
        val cameraPositionState = rememberCameraPositionState {
            var latLng = LatLng(41.31, 69.31)   // Tashkent location
            courierLocation?.let {
                latLng = LatLng(it.latitude, it.longitude)
            }
            position = CameraPosition.fromLatLngZoom(latLng, 13f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = LocalContext.current.hasLocationPermission()),
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            val iconFactory = IconGenerator(LocalContext.current)
            orders.forEach {
                iconFactory.setTextAppearance(R.style.mapIconTextStyle)
                if (it.ioStatus == OrderStatusEnum.STATUS_ON_WAY.st || it.ioStatus == OrderStatusEnum.STATUS_ARRIVED.st) {
                    iconFactory.setColor(getColorX(primaryX).toArgb())
                } else iconFactory.setColor(getColorX(secondaryX).toArgb())

                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    icon = BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("#${it.orderNumber}"))
                )
            }
        }

        val scale by rememberInfiniteTransition(label = "").animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(64.dp)
                .padding(12.dp)
                .scale(scale)
                .background(color = getColorX(colorName = secondBackgroundColorX), shape = RoundedCornerShape(26.dp))
                .clickable { openFullMapListener.invoke() }
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_main_fullscreen),
            contentDescription = "ZoomOut",
            colorFilter = ColorFilter.tint(color = getColorX(colorName = textColorX))
        )
    }
}
*/

