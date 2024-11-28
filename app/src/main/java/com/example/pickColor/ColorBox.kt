package com.example.pickColor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColorBox(
    colorName: String,
    isSelected: Boolean,
    size: Dp,
    showText: Boolean, // Parâmetro booleano para controlar a exibição do texto
    onClick: () -> Unit
) {
    val color = when (colorName) {
        "RED", "ROJO" -> Color.Red
        "BLUE", "AZUL" -> Color.Blue
        "GREEN", "VERDE" -> Color.Green
        "YELLOW", "AMARILLO" -> Color.Yellow
        "BLACK", "NEGRO" -> Color.Black
        "WHITE", "BLANCO" -> Color.White
        "ORANGE", "NARANJA" -> Color(0xFFFFA500) // Color.LTGRAY is used as a placeholder for Orange
        "PURPLE", "MORADO" -> Color(0xFF800080) // Color.LTGRAY is used as a placeholder for Purple
        "BROWN", "MARRÓN" -> Color(0xFF964B00) // Color.LTGRAY is used as a placeholder for Brown
        "PINK", "ROSA" -> Color(0xFFFFC0CB) // Color.LTGRAY is used as a placeholder for Pink
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(size)  // Usando o parâmetro de tamanho aqui
            .background(color, shape = RoundedCornerShape(15.dp))
            .clickable {
                onClick()  // Altera o estado de seleção
            }
            .border(
                width = 2.dp,
                color = if (isSelected) Color.Black else Color.Transparent,  // Aplica borda se selecionado
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center // Centraliza o conteúdo dentro da Box
    ) {
        if (showText) {
            Text(
                text = colorName,
                color = if (color == Color.White) Color.Black else Color.White, // Cor do texto (ajuste conforme necessário)
                style = MaterialTheme.typography.headlineLarge // Estilo do texto (ajuste conforme necessário)
            )
        }
    }
}



