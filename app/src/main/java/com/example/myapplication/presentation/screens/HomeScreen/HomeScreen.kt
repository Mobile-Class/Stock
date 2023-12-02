package com.example.myapplication.presentation.screens.HomeScreen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen() {
    LazyColumn {
        item {
            TrendingEarningsReportsBox()
        }
        item {
            TrendingStocksBox()
        }
        item {
            MostActivesBox()
        }
    }
}

@Composable
fun TrendingEarningsReportsBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                androidx.compose.material3.Text(
                    text = "Trending Earnings Reports",
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                )
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.material3.Text(
                    text = "More",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                )
            }
            // Dãy card chạy slide
            LazyRow {
                items(4) { cardIndex ->
                    TrendingEarningsCard(cardIndex)
                }
            }
        }
    }
}

@Composable
fun TrendingEarningsCard(cardIndex: Int) {
    val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(120.dp, 160.dp)
            .border(1.dp, androidx.compose.material3.MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)



    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Company Name
            androidx.compose.material3.Text(
                text = "Company $cardIndex",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Short Slogan
            androidx.compose.material3.Text(
                text = "Short Slogan",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Today's Date
            androidx.compose.material3.Text(
                text = "Today",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Today's Date
            androidx.compose.material3.Text(
                text = " ${currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))}",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}


@Composable
fun TrendingStocksBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            androidx.compose.material3.Text(
                text = "Trending Stocks",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(1.dp, color = Color.LightGray)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .size(72.dp)
                    .background(
                        shape = CircleShape,
                        brush = SolidColor(Color.Gray)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder image with circular border
                Image(
                    painter = painterResource(id = R.drawable.apple), // Replace with your actual image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background) // Change the background color as needed
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                androidx.compose.material3.Text(text = "Cannabis Stocks", style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(700)),)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material3.Text(text = "+2.90%", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(16.dp))
                    androidx.compose.material3.Text(text = "21 Symbols", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(36.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background))

            }        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(1.dp, color = Color.LightGray)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .size(72.dp)
                    .background(
                        shape = CircleShape,
                        brush = SolidColor(Color.Gray)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder image with circular border
                Image(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                androidx.compose.material3.Text(text = "Cannabis Stocks", style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(700)),)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material3.Text(text = "+2.90%", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(16.dp))
                    androidx.compose.material3.Text(text = "21 Symbols", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(36.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background))

            }        }
    }
}


@Composable
fun MostActivesBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        androidx.compose.material3.Text(
            text = "Most Actives",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
        )
        // Row 1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(){
                androidx.compose.material3.Text(text = "Apple",            style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(700)),
                )
            }
            Column(){
                androidx.compose.material3.Text(text = "Chart")
            }
            Column(
                horizontalAlignment = Alignment.End
            ){
                Row {
                    androidx.compose.material3.Text(text = "163.76")
                }
                Row {
                    androidx.compose.material3.Text(text = "-1.94%")
                }
                Row {
                    androidx.compose.material3.Text(text = "Post: -0.90%")
                }
            }
        }
        //Row 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(){
                androidx.compose.material3.Text(text = "Facebook",    style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(700)),)
            }
            Column(){
                androidx.compose.material3.Text(text = "Chart")
            }
            Column(
                horizontalAlignment = Alignment.End
            ){
                Row(
                ) {
                    androidx.compose.material3.Text(text = "163.76")
                }
                Row(
                ) {
                    androidx.compose.material3.Text(text = "-1.94%")
                }
                Row(
                ) {
                    androidx.compose.material3.Text(text = "Post: -0.90%")
                }
            }
        }

    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}