package com.example.myapplication.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.data.local.CompanyListingEntity
import com.example.myapplication.data.remote.dto.BalanceSheetAnnualReport
import com.example.myapplication.data.remote.dto.CashFlowAnnualReport
import com.example.myapplication.data.remote.dto.CompanyInfoDto
import com.example.myapplication.data.remote.dto.IncomeStatementAnnualReport
import com.example.myapplication.data.remote.dto.IntradayInfoDto
import com.example.myapplication.domain.model.CompanyBalanceSheet
import com.example.myapplication.domain.model.CompanyCashFlow
import com.example.myapplication.domain.model.CompanyIncomeStatement
import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}

fun BalanceSheetAnnualReport.toCompanyBalanceSheet() : CompanyBalanceSheet{
    return CompanyBalanceSheet(
        fiscalDateEnding = fiscalDateEnding ?: "",
        reportedCurrency = reportedCurrency ?: "",
        totalAssets = totalAssets ?: "",
        totalCurrentAssets = totalCurrentAssets ?: "",
        cashAndCashEquivalentsAtCarryingValue =  cashAndCashEquivalentsAtCarryingValue ?: "",
        cashAndShortTermInvestments= cashAndShortTermInvestments ?: "",
        inventory = inventory ?: "",
        currentNetReceivables =  currentNetReceivables ?: "",
        totalNonCurrentAssets = totalNonCurrentAssets ?: "",
        propertyPlantEquipment = propertyPlantEquipment ?: "",
        accumulatedDepreciationAmortizationPPE = accumulatedDepreciationAmortizationPPE ?: "",
        intangibleAssets = intangibleAssets ?: "",
        intangibleAssetsExcludingGoodwill = intangibleAssetsExcludingGoodwill ?: "",
        goodwill = goodwill ?: "",
        investments = investments ?: "",
        longTermInvestments = longTermInvestments ?: "",
        shortTermInvestments = shortTermInvestments ?: "",
        otherCurrentAssets = otherCurrentAssets ?: "",
        otherNonCurrentAssets = otherNonCurrentAssets ?: "",
        totalLiabilities = totalLiabilities ?: "",
        totalCurrentLiabilities = totalCurrentLiabilities ?: "",
        currentAccountsPayable = currentAccountsPayable ?: "",
        deferredRevenue = deferredRevenue ?: "",
        currentDebt = currentDebt ?: "",
        shortTermDebt = shortTermDebt ?: "",
        totalNonCurrentLiabilities = totalNonCurrentLiabilities ?: "",
        capitalLeaseObligations = capitalLeaseObligations ?: "",
        longTermDebt = longTermDebt ?: "",
        currentLongTermDebt = currentLongTermDebt ?: "",
        longTermDebtNoncurrent = longTermDebtNoncurrent ?: "",
        shortLongTermDebtTotal = shortLongTermDebtTotal ?: "",
        otherCurrentLiabilities = otherCurrentLiabilities ?: "",
        otherNonCurrentLiabilities=otherNonCurrentLiabilities ?: "",
        totalShareholderEquity = totalShareholderEquity ?: "",
        treasuryStock = treasuryStock ?: "",
        retainedEarnings = retainedEarnings ?: "",
        commonStock = commonStock ?: "",
        commonStockSharesOutstanding = commonStockSharesOutstanding ?: ""
    )
}

fun IncomeStatementAnnualReport.toCompanyIncomeStatement() : CompanyIncomeStatement{
    return CompanyIncomeStatement(
        fiscalDateEnding = fiscalDateEnding ?: "",
        reportedCurrency = reportedCurrency ?: "",
        grossProfit = grossProfit ?: "",
        totalRevenue = totalRevenue ?: "",
        costOfRevenue =  costOfRevenue ?: "",
        costofGoodsAndServicesSold= costofGoodsAndServicesSold ?: "",
        operatingIncome = operatingIncome ?: "",
        sellingGeneralAndAdministrative =  sellingGeneralAndAdministrative ?: "",
        researchAndDevelopment = researchAndDevelopment ?: "",
        operatingExpenses = operatingExpenses ?: "",
        investmentIncomeNet = investmentIncomeNet ?: "",
        netInterestIncome = netInterestIncome ?: "",
        interestIncome = interestIncome ?: "",
        interestExpense = interestExpense ?: "",
        nonInterestIncome = nonInterestIncome ?: "",
        otherNonOperatingIncome = otherNonOperatingIncome ?: "",
        depreciation = depreciation ?: "",
        depreciationAndAmortization = depreciationAndAmortization ?: "",
        incomeBeforeTax = incomeBeforeTax ?: "",
        incomeTaxExpense = incomeTaxExpense ?: "",
        interestAndDebtExpense = interestAndDebtExpense ?: "",
        netIncomeFromContinuingOperations = netIncomeFromContinuingOperations ?: "",
        comprehensiveIncomeNetOfTax = comprehensiveIncomeNetOfTax ?: "",
        ebit = ebit ?: "",
        ebitda = ebitda ?: "",
        netIncome = netIncome ?: ""
    )
}

fun CashFlowAnnualReport.toCompanyCashFlow() : CompanyCashFlow{
    return CompanyCashFlow(
        fiscalDateEnding = fiscalDateEnding ?: "",
        reportedCurrency = reportedCurrency ?: "",
        operatingCashflow = operatingCashflow ?: "",
        paymentsForOperatingActivities = paymentsForOperatingActivities ?: "",
        proceedsFromOperatingActivities =  proceedsFromOperatingActivities ?: "",
        changeInOperatingLiabilities= changeInOperatingLiabilities ?: "",
        changeInOperatingAssets = changeInOperatingAssets ?: "",
        depreciationDepletionAndAmortization =  depreciationDepletionAndAmortization ?: "",
        capitalExpenditures = capitalExpenditures ?: "",
        changeInReceivables = changeInReceivables ?: "",
        changeInInventory = changeInInventory ?: "",
        profitLoss = profitLoss ?: "",
        cashflowFromInvestment = cashflowFromInvestment ?: "",
        cashflowFromFinancing = cashflowFromFinancing ?: "",
        proceedsFromRepaymentsOfShortTermDebt = proceedsFromRepaymentsOfShortTermDebt ?: "",
        paymentsForRepurchaseOfCommonStock = paymentsForRepurchaseOfCommonStock ?: "",
        paymentsForRepurchaseOfEquity = paymentsForRepurchaseOfEquity ?: "",
        paymentsForRepurchaseOfPreferredStock = paymentsForRepurchaseOfPreferredStock ?: "",
        dividendPayout = dividendPayout ?: "",
        dividendPayoutCommonStock = dividendPayoutCommonStock ?: "",
        dividendPayoutPreferredStock = dividendPayoutPreferredStock ?: "",
        proceedsFromIssuanceOfCommonStock = proceedsFromIssuanceOfCommonStock ?: "",
        proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet ?: "",
        proceedsFromIssuanceOfPreferredStock = proceedsFromIssuanceOfPreferredStock ?: "",
        proceedsFromRepurchaseOfEquity = proceedsFromRepurchaseOfEquity ?: "",
        proceedsFromSaleOfTreasuryStock = proceedsFromSaleOfTreasuryStock ?: "",
        changeInCashAndCashEquivalents = changeInCashAndCashEquivalents ?: "",
        changeInExchangeRate = changeInExchangeRate ?: "",
        netIncome = netIncome ?: ""
    )
}

//@RequiresApi(Build.VERSION_CODES.O)
//fun IntradayInfoDto.toIntradayList(): List<IntradayInfo> {
//    val pattern = "yyyy-MM-dd HH:mm:ss"
//    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
//
//    return timeSeries.entries.mapNotNull { entry ->
//        val timestamp = entry.key.timestamp
//        val localDateTime = LocalDateTime.parse(timestamp, formatter)
//        val close = entry.value.close.toDoubleOrNull()
//
//        // Only include entries where close can be converted to Double
//        if (close != null) {
//            IntradayInfo(
//                date = localDateTime,
//                close = close
//            )
//        } else {
//            null
//        }
//    }
//}

//@RequiresApi(Build.VERSION_CODES.O)
//fun IntradayInfoDto.toIntraday() : IntradayInfo {
//    val pattern = "yyyy-MM-dd HH:mm:ss"
//    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
//    val localDateTime = LocalDateTime.parse(, formatter)
//    return IntradayInfo(
//        date = localDateTime,
//        close = close
//    )
//}