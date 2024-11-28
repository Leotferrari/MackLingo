package com.example.pickColor

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AnimalsScreen(
    navController: NavHostController,
    selectedFlag: String?,
    yellowStarsCount: Int = 0
) {
    val context = LocalContext.current

    val animals = when (selectedFlag) {
        "USA" -> listOf("Monkey", "Elephant")  // Inglês
        "Spain" -> listOf("Mono", "Elefante")  // Espanhol
        else -> listOf("", "")
    }

    val questionImage by remember { mutableStateOf(animals.random()) }
    var selectedImage by remember { mutableStateOf("") }
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
            text = if (selectedFlag == "USA") "Which animal is this?" else "¿Qué animal es este?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(75.dp))

        Text(
            text = questionImage ?: "",
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
            animals.forEach { animal ->
                ImageBox(
                    imageName = animal,
                    isSelected = selectedImage == animal,
                    size = 136.dp
                ) {
                    selectedImage = animal
                }
            }
        }

        Spacer(modifier = Modifier.height(98.dp))

        Button(
            onClick = {
                if (selectedImage == questionImage) {
                    starCount += 1
                    navController.navigate("third_screen/${selectedFlag}/${selectedImage}/${starCount}")
                } else {
                    Toast.makeText(context, "Opção errada! escolha novamente.", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = selectedImage.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(width = 216.dp, height = 63.dp)
        ) {
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

@Composable
fun ImageBox(
    imageName: String,
    isSelected: Boolean,
    size: Dp,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color.Black else Color.Transparent
    val imageRes = when (imageName.lowercase()) {
        "monkey", "mono" -> R.drawable.monkey
        "elephant", "elefante" -> R.drawable.elephant
        else -> 0
    }

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.White)
            .border(2.dp, borderColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (imageRes != 0) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(size - 16.dp)
            )
        }
    }
}
