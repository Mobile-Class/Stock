package com.example.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class CompanyBalanceSheetDto(
    @Json(name = "symbol") val symbol: String,
    @Json(name = "annualReports") val annualReports: List<BalanceSheetAnnualReport>
)

data class BalanceSheetAnnualReport(
    @field:Json(name = "fiscalDateEnding") val fiscalDateEnding : String?,
    @field:Json(name = "reportedCurrency") val reportedCurrency: String?,
    @field:Json(name = "totalAssets")  val totalAssets: String?,
    @field:Json(name = "totalCurrentAssets") val totalCurrentAssets: String?,
    @field:Json(name = "cashAndCashEquivalentsAtCarryingValue") val cashAndCashEquivalentsAtCarryingValue: String?,
    @field:Json(name = "cashAndShortTermInvestments") val cashAndShortTermInvestments: String?,
    @field:Json(name = "inventory") val inventory: String?,
    @field:Json(name = "currentNetReceivables") val currentNetReceivables: String?,
    @field:Json(name = "totalNonCurrentAssets") val totalNonCurrentAssets: String?,
    @field:Json(name = "propertyPlantEquipment") val propertyPlantEquipment: String?,
    @field:Json(name = "accumulatedDepreciationAmortizationPPE") val accumulatedDepreciationAmortizationPPE: String?,
    @field:Json(name = "intangibleAssets") val intangibleAssets: String?,
    @field:Json(name = "intangibleAssetsExcludingGoodwill") val intangibleAssetsExcludingGoodwill: String?,
    @field:Json(name = "goodwill") val goodwill: String?,
    @field:Json(name = "investments") val investments: String?,
    @field:Json(name = "longTermInvestments") val longTermInvestments: String?,
    @field:Json(name = "shortTermInvestments") val shortTermInvestments: String?,
    @field:Json(name = "otherCurrentAssets") val otherCurrentAssets: String?,
    @field:Json(name = "otherNonCurrentAssets") val otherNonCurrentAssets: String?,
    @field:Json(name = "totalLiabilities") val totalLiabilities: String?,
    @field:Json(name = "totalCurrentLiabilities") val totalCurrentLiabilities: String?,
    @field:Json(name = "currentAccountsPayable") val currentAccountsPayable: String?,
    @field:Json(name = "deferredRevenue") val deferredRevenue: String?,
    @field:Json(name = "currentDebt") val currentDebt: String?,
    @field:Json(name = "shortTermDebt") val shortTermDebt: String?,
    @field:Json(name = "totalNonCurrentLiabilities") val totalNonCurrentLiabilities: String?,
    @field:Json(name = "capitalLeaseObligations") val capitalLeaseObligations: String?,
    @field:Json(name = "longTermDebt") val longTermDebt: String?,
    @field:Json(name = "currentLongTermDebt") val currentLongTermDebt: String?,
    @field:Json(name = "longTermDebtNoncurrent") val longTermDebtNoncurrent: String?,
    @field:Json(name = "shortLongTermDebtTotal") val shortLongTermDebtTotal: String?,
    @field:Json(name = "otherCurrentLiabilities") val otherCurrentLiabilities: String?,
    @field:Json(name = "otherNonCurrentLiabilities") val otherNonCurrentLiabilities: String?,
    @field:Json(name = "totalShareholderEquity") val totalShareholderEquity: String?,
    @field:Json(name = "treasuryStock") val treasuryStock: String?,
    @field:Json(name = "retainedEarnings") val retainedEarnings: String?,
    @field:Json(name = "commonStock") val commonStock: String?,
    @field:Json(name = "commonStockSharesOutstanding") val commonStockSharesOutstanding: String?
)