package sheen.dev.jetpackcomposetictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(20.dp)
    ){
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.Red,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            onClick = {  }
        ) {
            Text(text = "Single Player")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.Red,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            onClick = {  }
        ) {
            Text(text = "Two Players")
        }

        Row {
            // cat
            // dog
            // something else
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}