package com.example.pickColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 3,
    starsColor: Color = Color.Yellow,
) {
    // Assegurar que a avaliação não excede o número de estrelas disponíveis
    val filledStars = min(rating, stars)
    val unfilledStars = stars - filledStars

    Row(modifier = modifier, Arrangement.spacedBy(15.dp)) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Gray // ou outra cor para estrelas não preenchidas
            )
        }
    }
}

@Preview()
@Composable
fun RatingExample() {
    RatingBar(rating = 1, starsColor = Color.Yellow)
}

