package com.example.pickColor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pickColor.ui.theme.LimeGreen

@Composable
fun CongratulationsScreen(
    navController: NavHostController,
    selectedFlag: String,
    selectedColor: String,
    ratingCount: Int
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        RatingBar(rating = ratingCount ,starsColor = Color.Yellow)
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .width(332.dp)
                .height(1.dp)
                .background(color = Color.DarkGray)
        )

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = if (selectedFlag == "USA") "CONGRATULATIONS!" else "Felicidades",
            style = MaterialTheme.typography.headlineMedium.copy(color = LimeGreen),
        )

        Spacer(modifier = Modifier.height(45.dp))

        ColorBox(
            colorName = selectedColor,
            isSelected = false,
            showText = true,
            size = 210.dp
        ) { }
        // not valid in this scope

        Spacer(modifier = Modifier.height(38.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier =  Modifier.size(77.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    if (ratingCount == 1) {
                        navController.navigate("fourth_screen/${selectedFlag}/${ratingCount}")
                    } else {
                        navController.navigate("second_screen/${selectedFlag}/${ratingCount}")
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
                    text = if (selectedFlag == "USA") "CONTINUE" else "CONTINUAR",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )
            }

        }
    }
}   