package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.componnent.card.CustomButton

class DeleteAllUsersDialog (
    val delete: () -> Unit = {},
    val dismiss: () -> Unit = {},
    val blockCancel: () -> Unit = {}
) : Screen {
    @Composable
    override fun Content() {
        DeleteAllUsersDialogContent(
            delete = delete,
            dismiss = dismiss,
            blockCancel = blockCancel
        )
    }
}
@Composable
private fun DeleteAllUsersDialogContent(
    delete: () -> Unit = {},
    dismiss: () -> Unit = {},
    blockCancel: () -> Unit = {}
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
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.TopEnd)
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) { blockCancel() }
                    .background(color = Color(0xFFD6D5D5), shape = RoundedCornerShape(100))
                    .size(32.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.ic_cencel),
                    contentDescription = null,
                    tint = Color(0xFF616060)
                )
            }
        }

        CustomTextView(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.delete_user),
            fontSize = 20,
            textAlign = TextAlign.Left
        )

        CustomTextView(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.ac_delete),
            fontSize = 16,
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
                text = stringResource(id = R.string.delete_all),
                fontSize = 16,
                fontWeight = 600,
                textColor = Color(0xFFBE4949),
                colors = ButtonDefaults
                    .buttonColors(Color(0xFFFFCBCB))
            ) { delete() }
            CustomButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(48.dp)
                    .border(
                        BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(100)
                    ),
                text = stringResource(id = R.string.no),
                fontSize = 16,
                fontWeight = 600,
                textColor = Color.Black,
                colors = ButtonDefaults
                    .buttonColors(Color.White)
            ) { dismiss() }
        }

    }
}