package com.example.mobilebanking.presentantion.screen.pager.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.myLog
import org.orbitmvi.orbit.compose.collectAsState


object HistoryPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Tarix"
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_operations_timecircle))

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel: HistoryContract.Model = getViewModel<HistoryModel>()

        myLog("PaymentPage -> $this")
        myLog("PaymentPage screenModel -> $screenModel")

        MobileBankingTheme {
            val uiState = screenModel.collectAsState().value
            PaymentContent(uiState = uiState, onEventDispatcher = screenModel::onEventDispatcher)
        }
    }
}

@Composable
private fun PaymentContent(
    uiState: HistoryContract.UIState,
    onEventDispatcher: (HistoryContract.Intent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "PaymentPage",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PaymentContentPreview() {
    MobileBankingTheme {
        PaymentContent(uiState = HistoryContract.UIState.InitState) {}
    }
}



