package com.example.myapplication.presentation.screens.DetailScreen.EvaluationScreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.screens.DetailScreen.CompanyInfoViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.CompanyBalanceSheet
import com.example.myapplication.domain.model.CompanyCashFlow
import com.example.myapplication.domain.model.CompanyIncomeStatement
import com.example.myapplication.presentation.screens.DetailScreen.cashFlowDexription
import com.example.myapplication.presentation.screens.DetailScreen.ltlDesc
import com.example.myapplication.presentation.screens.DetailScreen.netIncomeDesc
import com.example.myapplication.presentation.screens.DetailScreen.peDescription
import com.example.myapplication.presentation.screens.DetailScreen.revenueDesc
import com.example.myapplication.presentation.screens.DetailScreen.roicDexription
import com.example.myapplication.presentation.screens.DetailScreen.sharesDescription
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.Gr
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun EvaluationScreen(symbol: String,  viewModel: CompanyInfoViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gr),
        contentAlignment = Alignment.Center
    ) {
        val pe: String? = viewModel.state.company?.pe
        val peChange: Boolean = pe?.toDoubleOrNull()?.let { it < 22.5 } ?: false

        val sharesChanged : Double = calculateShares(viewModel.state.balanceSheet)
        val shareChange : Boolean = sharesChanged <= 0.0

        val revenueChanged = calculateRevenueGrowth(viewModel.state.incomeStatement)
        val revenueChange : Boolean = revenueChanged > 0.0

        val cashFLowChanged = calculateCashFlowGrowth(viewModel.state.cashFlow)
        val cashFlowChange : Boolean = cashFLowChanged > 0.0

        val incomeChanged = calculateNetIncomeChange(viewModel.state.incomeStatement)
        val incomeChange : Boolean = incomeChanged > 0.0

        val marketCap = viewModel.state.company?.marketCap?.toDoubleOrNull() ?: 0.0
        val cashFlowList = viewModel.state.cashFlow
        val cashFl = if (cashFlowList.isNotEmpty()) cashFlowList.first().operatingCashflow?.toDoubleOrNull() ?: 0.0 else 0.0
        val priceToCashFlow: Double = if (cashFl != 0.0) marketCap / cashFl else 100.0
        val priceToCashFlowChange = priceToCashFlow < 20.0
        val priceToCashFlowString = if(priceToCashFlow != 100.0) String.format("%.4f", priceToCashFlow) else "-"

        val totalLiability = viewModel.state.balanceSheet.firstOrNull()?.totalLiabilities?.toDoubleOrNull() ?: 0.0
        val cashFlowFirstItem = viewModel.state.cashFlow.firstOrNull()
        val operatingCashflow = cashFlowFirstItem?.operatingCashflow?.toDoubleOrNull() ?: 0.0
        val capitalExpenditures = cashFlowFirstItem?.capitalExpenditures?.toDoubleOrNull() ?: 0.0
        val freeCashFlow = operatingCashflow - capitalExpenditures
        val ltlfcf = if (freeCashFlow != 0.0) totalLiability / freeCashFlow else Double.POSITIVE_INFINITY
        val ltlfcfChange = ltlfcf < 5.0

        val income = viewModel.state.incomeStatement.firstOrNull()?.operatingIncome?.toDoubleOrNull() ?: 0.0
        val invested = viewModel.state.balanceSheet.firstOrNull()?.investments?.toDoubleOrNull() ?: 0.0
        val roic = if (invested != 0.0) income / invested * 15 else 0.0
        val roicChange = roic > 9.0


        val pillars = listOf(
            PillarData("5YR P/E Ratio < 22.5", pe,  peChange, peDescription),
            PillarData("5YR ROIC > 9%", String.format("%.2f", roic) + "%", roicChange, roicDexription),
            PillarData("Shares Outstanding Decreasing", "$sharesChanged%", shareChange, sharesDescription),
            PillarData("Cash Flow Growth 5 Yr", viewModel.state.cashFlow.first().operatingCashflow + "$" , cashFlowChange, cashFlowDexription),
            PillarData("5YR Price to Free Cash Flow < 20", priceToCashFlowString , priceToCashFlowChange, cashFlowDexription),
            PillarData("Net Income Growth 5Yr", viewModel.state.incomeStatement.first().netIncome + "$", incomeChange, netIncomeDesc),
            PillarData("LTL/ 5Yr FCF < 5", String.format("%.4f", ltlfcf),  ltlfcfChange, ltlDesc),
            PillarData("Revenue Growth 5Yr", viewModel.state.incomeStatement.first().totalRevenue + "$",  revenueChange, revenueDesc)
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
                        text = pillar.value ?: "-",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (pillar.isPositive) DarkGreen else Color.Red
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
    val value: String?,
    val isPositive: Boolean,
    val description: String
)

fun calculateShares(balanceSheet : List <CompanyBalanceSheet>) : Double{
    if (balanceSheet.size < 5) {
        return 0.0
    }

    val lastFiveYearsShares = balanceSheet.reversed().takeLast(5).mapNotNull { it.commonStockSharesOutstanding.toLongOrNull() }
    if (lastFiveYearsShares.size < 5) {
        return 0.0
    }

    val oldestYearShares = BigDecimal(lastFiveYearsShares.first())
    val mostRecentYearShares = BigDecimal(lastFiveYearsShares.last())

    return mostRecentYearShares.subtract(oldestYearShares)
        .divide(oldestYearShares, 4, RoundingMode.HALF_UP)
        .multiply(BigDecimal(100))
        .toDouble()
}

fun calculateRevenueGrowth(incomeStatement : List <CompanyIncomeStatement>) : Double{
    if (incomeStatement.size < 5) {
        return 0.0
    }

    val lastFiveYearsRevenue = incomeStatement.reversed().takeLast(5).mapNotNull { it.totalRevenue.toLongOrNull()}
    if (lastFiveYearsRevenue.size < 5) {
        return 0.0
    }

    val oldestYearRevenue = BigDecimal(lastFiveYearsRevenue.first())
    val mostRecentYearRevenue = BigDecimal(lastFiveYearsRevenue.last())
    Log.e("Revenue", oldestYearRevenue.toString() + " ----- " + mostRecentYearRevenue.toString())
    return mostRecentYearRevenue.subtract(oldestYearRevenue)
        .divide(oldestYearRevenue, 4, RoundingMode.HALF_UP)
        .multiply(BigDecimal(100))
        .toDouble()
}

fun calculateCashFlowGrowth(cashFlow : List <CompanyCashFlow>) : Double{
    if (cashFlow.size < 5) {
        return 0.0
    }

    val lastFiveYearsCashFlow = cashFlow.reversed().takeLast(5).mapNotNull { it.operatingCashflow.toLongOrNull()}
    if (lastFiveYearsCashFlow.size < 5) {
        return 0.0
    }

    val oldestYearCashFlow = BigDecimal(lastFiveYearsCashFlow.first())
    val mostRecentYearCashFlow = BigDecimal(lastFiveYearsCashFlow.last())
    Log.e("Revenue", oldestYearCashFlow.toString() + " ----- " + mostRecentYearCashFlow.toString())
    return mostRecentYearCashFlow.subtract(oldestYearCashFlow)
        .divide(oldestYearCashFlow, 4, RoundingMode.HALF_UP)
        .multiply(BigDecimal(100))
        .toDouble()
}

fun calculateNetIncomeChange(incomeStatement : List <CompanyIncomeStatement>) : Double{
    if (incomeStatement.size < 5) {
        return 0.0
    }

    val lastFiveYearsIncome = incomeStatement.reversed().takeLast(5).mapNotNull { it.netIncome.toLongOrNull()}
    if (lastFiveYearsIncome.size < 5) {
        return 0.0
    }

    val oldestYearIncome = BigDecimal(lastFiveYearsIncome.first())
    val mostRecentYearIncome = BigDecimal(lastFiveYearsIncome.last())
    Log.e("Income", oldestYearIncome.toString() + " ----- " + mostRecentYearIncome.toString())
    return mostRecentYearIncome.subtract(oldestYearIncome)
        .divide(oldestYearIncome, 4, RoundingMode.HALF_UP)
        .multiply(BigDecimal(100))
        .toDouble()
}