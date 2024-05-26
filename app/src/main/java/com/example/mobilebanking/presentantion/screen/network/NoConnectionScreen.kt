package com.example.mobilebanking.presentantion.screen.network

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.MainActivity
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.card.CustomButton
import com.example.mobilebanking.ui.theme.selectedItemColor
import com.example.mobilebanking.util.MyDataLoader
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.compose.collectSideEffect

class NoConnectionScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NoConnectionContract.ViewModel = getViewModel<NoConnectionViewModel>()
        val scope = rememberCoroutineScope()
        viewModel.collectSideEffect { sideEffect ->
            when(sideEffect) {
                NoConnectionContract.SideEffect.Refresh -> {
                    MainActivity.NetworkStatus.hasNetwork.onEach {
                        if (it) {
                            MyDataLoader.loadCardsData()
                            viewModel.onEventDispatcher(NoConnectionContract.Intent.Back)
                        }
                    }.launchIn(scope)
                }
            }
        }
        NoConnectionContent(
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun NoConnectionContent(
    onEventDispatcher: (NoConnectionContract.Intent) -> Unit
) {
    BackHandler {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //DatePickerCompose()


        Spacer(modifier = Modifier.height(108.dp))

        Image(
            painterResource(id = R.drawable.app_logo),
            contentDescription = "MusicDisk",
            modifier = Modifier
                .padding(4.dp)
                .width(64.dp)
                .height(64.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = R.string.not_connected),
            fontFamily = FontFamily(
                Font(R.font.pnfont_semibold)
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            color = Color.Black,
            fontSize = TextUnit(26f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.no_con_desc),
            fontFamily = FontFamily(
                Font(R.font.pnfont_semibold)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp, horizontal = 42.dp),
            color = Color(0XFF988E8E),
            fontSize = TextUnit(16f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.refresh),
            fontSize = 20,
            fontWeight = 600,
            enabled = true,
            colors = ButtonDefaults.buttonColors(selectedItemColor),
            click = { onEventDispatcher(NoConnectionContract.Intent.Refresh) },
            textColor = Color.White
        )

        Spacer(modifier = Modifier.padding(8.dp))

    }
}