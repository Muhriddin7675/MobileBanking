import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.aviaCardColor
import com.example.mobilebanking.ui.theme.aviaTextColor
import com.example.mobilebanking.ui.theme.white

@Composable
fun CardAvia(
//    clickButton: () -> Unit,
//    buttonText: String,
//    textTitle: String,
//    clickCardRoot: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(aviaCardColor)
    )
    {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.paynet),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Bottom)
                    )
                    Text(
                        text = "paynet",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    )
                    Text(
                        text = " avia",
                        color = aviaTextColor,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    )


                }
                Text(
                    text = stringResource(id = R.string.foydali_ishonchli),
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            bottom = 12.dp,
                            top = 8.dp
                        )
                        .height(32.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(white),

                    ) {
                    Text(
                        text = stringResource(id = R.string.chipta_sotib),
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular))
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1.1f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(R.drawable.paynet_avia_image),
                    contentDescription = "security",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(120.dp)
                )

            }
        }
    }
}

@Preview
@Composable
fun FunctionPreview() {
    MobileBankingTheme {
        CardAvia()
    }

}


