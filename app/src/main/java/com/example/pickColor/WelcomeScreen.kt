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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val selectedFlag = remember { mutableStateOf<String?>(null) }
    val yellowStarsCount = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(94.dp))

        Image(
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = "example image",
            modifier = Modifier
                .padding(16.dp)
                .width(360.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(90.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            FlagBox(
                painter = painterResource(id = R.drawable.united_states_flag_icon),
                contentDescription = "USA",
                isSelected = selectedFlag.value == "USA",  // Verifica se a bandeira dos EUA está selecionada
                selectedFlag = selectedFlag
            )
            Spacer(modifier = Modifier.width(19.dp))
            FlagBox(
                painter = painterResource(id = R.drawable.spain_country_flag_icon),
                contentDescription = "Spain",
                isSelected = selectedFlag.value == "Spain",  // Verifica se a bandeira da Espanha está selecionada
                selectedFlag = selectedFlag
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        Button(
            onClick = {
                if (!selectedFlag.value.isNullOrEmpty()) {
                    navController.navigate("second_screen/${selectedFlag.value}/${yellowStarsCount.value}")
                } else {
                    // Mostre uma mensagem de erro ou feedback visual
                     Toast.makeText(context, "Por favor, selecione uma bandeira.", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(width = 256.dp, height = 63.dp)
        )
        {
            Text(
                text = "COMEÇAR A APRENDER",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            )

        }
        Spacer(modifier = Modifier.height(114.dp))
    }
}

@Composable
fun FlagBox(painter: Painter, contentDescription: String, isSelected: Boolean, selectedFlag: MutableState<String?>) {
    Box(
        modifier = Modifier
            .size(width = 120.dp, height = 84.dp)
            .border(0.dp, Color.Gray, RoundedCornerShape(18.dp))
            .clickable {
                selectedFlag.value = contentDescription
            }
            .border(
                width = 2.dp,
                color = if (isSelected) Color.Black else Color.Transparent,  // Aplica borda se selecionado
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))

        )
    }
}