package com.example.myapplication.presentation.screens.DetailScreen.EvaluationScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.screens.DetailScreen.CompanyInfoViewModel
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Gr

@Composable
fun EvaluationScreen(symbol: String,  viewModel: CompanyInfoViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gr),
        contentAlignment = Alignment.Center
    ) {
        val pillars = listOf(
            PillarData("5YR P/E Ratio", "43.98", "-1,95%", false, "Detailed description for 5YR P/E Ratio."),
            PillarData("5YR ROIC", "94%", "+1,02%", true, "Detailed description for 5YR P/E Ratio."),
            PillarData("Shares Outstanding", "32.45%", "-1,27%", false, "Detailed description for 5YR P/E Ratio."),
            PillarData("Cash Flow Growth 5 Yr", "814.3M", "+1,02%", true, "Detailed description for 5YR P/E Ratio."),
            PillarData("5YR Price to Free Cash", "28.58", "-1,27%", false, "Detailed description for 5YR P/E Ratio."),
            PillarData("Net Income Growth 5Yr", "527.4M", "+1,02%", true, "Detailed description for 5YR P/E Ratio."),
            PillarData("LTL/ 5YrFCF", "7.2", "-1,4%", false, "Detailed description for 5YR P/E Ratio."),
            PillarData("Revenue Growth 5Yr", "2.21B", "+1,02%", true, "Detailed description for 5YR P/E Ratio.")
        )
        var expandedCardId by remember { mutableStateOf(-1) }

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(pillars) { index, pillar ->
                PillarCard(pillar, index == expandedCardId) {
                    expandedCardId = if (expandedCardId == index) -1 else index
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}@Composable
fun PillarCard(pillar: PillarData, isExpanded: Boolean, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pillar.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Display the value with the corresponding image
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = pillar.value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (pillar.isPositive) Color.Green else Color.Red
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = if (pillar.isPositive) R.drawable.checkmark else R.drawable.xmark),
                        contentDescription = if (pillar.isPositive) "Positive" else "Negative",
                        modifier = Modifier
                            .size(36.dp) // Set the size of the image
                    )
                }

            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = pillar.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

data class PillarData(
    val title: String,
    val value: String,
    val change: String,
    val isPositive: Boolean,
    val description: String
)