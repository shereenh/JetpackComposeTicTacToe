package sheen.dev.jetpackcomposetictactoe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DrawBox(
//    content : @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(Color.Green)
            .border(BorderStroke(2.dp, SolidColor(Color.Red)))
    ) {
//        content()
    }
}


@Composable
fun GameScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Player: ")
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            DrawBox()
            DrawBox()
            DrawBox()
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            DrawBox()
            DrawBox()
            DrawBox()
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            DrawBox()
            DrawBox()
            DrawBox()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}