package com.example.myapplication.presentation.screens.DetailScreen.ProfileScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.screens.DetailScreen.CompanyInfoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(symbol: String, viewModel: CompanyInfoViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
//            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        val state = viewModel.state
        if(state.error == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(Color.White)
                    .padding(18.dp)
            ) {
                state.company?.let { company ->
                    Text(
                        text = company.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
//                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = company.symbol,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Industry: ${company.industry}",
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Country: ${company.country}",
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = company.description,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    if(state.stockInfos.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Market Summary")
                        Spacer(modifier = Modifier.height(16.dp))
                        val first24StockInfos = state.stockInfos.take(24)

                        StockChart(
                            info = first24StockInfos,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(125.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if(state.isLoading) {
                CircularProgressIndicator()
            } else if(state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}