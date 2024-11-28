package com.example.pickColor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun QuizScreen(
    navController: NavHostController,
    selectedFlag: String?,
    yellowStarsCount: Int = 0
) {
    val context = LocalContext.current

    val colors = when (selectedFlag) {
        "USA" -> listOf("RED", "BLUE", "GREEN", "YELLOW", "BLACK", "WHITE", "ORANGE", "PURPLE", "BROWN", "PINK")  // Inglês
        "Spain" -> listOf("ROJO", "AZUL", "VERDE", "AMARILLO", "NEGRO", "BLANCO", "NARANJA", "MORADO", "MARRÓN", "ROSA")  // Espanhol
        else -> listOf("", "")
    }

    val questionColor by remember { mutableStateOf(colors.random()) }
    val otherColor by remember {
        mutableStateOf(colors.filter { it != questionColor }.random())
    }
    var selectedColor by remember { mutableStateOf("") }
    var starCount = yellowStarsCount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        RatingBar(rating = starCount, starsColor = Color.Yellow)
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .width(332.dp)
                .height(1.dp)
                .background(color = Color.DarkGray)
        )

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = if (selectedFlag == "USA") "What is this color?" else "¿Cuál es este color?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(75.dp))

        Text(
            text = questionColor ?: "",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp
            )
        )
        Spacer(modifier = Modifier.height(75.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(questionColor, otherColor).forEach { color ->
                ColorBox(
                    colorName = color,
                    isSelected = selectedColor == color,
                    showText = false,
                    size = 136.dp
                ) {
                    selectedColor = color
                }
            }
        }
        Spacer(modifier = Modifier.height(98.dp))
        Button(
            onClick = {
                if (selectedColor == questionColor) {
                    starCount += 1
                    navController.navigate("third_screen/${selectedFlag}/${selectedColor}/${starCount}")
                } else {
                    Toast.makeText(context, "Opção errada! escolha novamente.", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = selectedColor.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(width = 216.dp, height = 63.dp)
        )
        {
            Text(
                text = if (selectedFlag == "USA") "SELECT" else "SELECCIONAR",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            )
        }
    }
}
