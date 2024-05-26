package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.card.CustomButton
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.theme.selectedItemColor

class LogOutDialog (
    val logOut: () -> Unit = {},
    val dismiss: () -> Unit = {},
) : Screen {
    @Composable
    override fun Content() {
        DeleteCardDialogContent(
            logOut = logOut,
            dismiss = dismiss,
        )
    }

}

@Composable
private fun DeleteCardDialogContent(
    logOut: () -> Unit = {},
    dismiss: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
                    .width(30.dp)
                    .height(4.dp)
                    .background(Color(0xFFADADAD), shape = RoundedCornerShape(4.dp))
            )

        }

        CustomTextView(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.log_out_d),
            fontSize = 20,
            textAlign = TextAlign.Left
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 18.dp)
                .fillMaxWidth()
        ) {
            CustomButton(
                modifier = Modifier
                    .height(48.dp),
                text = stringResource(id = R.string.log_out_),
                fontSize = 16,
                fontWeight = 600,
                textColor = Color.White,
                colors = ButtonDefaults
                    .buttonColors(selectedItemColor)
            ) { logOut() }
            CustomButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(48.dp)
                    .border(BorderStroke(2.dp, selectedItemColor), shape = RoundedCornerShape(100)),
                text = stringResource(id = R.string.no),
                fontSize = 16,
                fontWeight = 600,
                textColor = selectedItemColor,
                colors = ButtonDefaults
                    .buttonColors(Color.White)
            ) { dismiss() }
        }

    }
}