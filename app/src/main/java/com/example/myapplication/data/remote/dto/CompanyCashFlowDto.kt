package com.example.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class CompanyCashFlowDto(
    @Json(name = "symbol") val symbol: String,
    @Json(name = "annualReports") val annualReports: List<CashFlowAnnualReport>
)

data class CashFlowAnnualReport(
    @field:Json(name = "fiscalDateEnding") val fiscalDateEnding : String?,
    @field:Json(name = "reportedCurrency") val reportedCurrency: String?,
    @field:Json(name = "operatingCashflow")  val operatingCashflow: String?,
    @field:Json(name = "paymentsForOperatingActivities") val paymentsForOperatingActivities: String?,
    @field:Json(name = "proceedsFromOperatingActivities") val proceedsFromOperatingActivities: String?,
    @field:Json(name = "changeInOperatingLiabilities") val changeInOperatingLiabilities: String?,
    @field:Json(name = "changeInOperatingAssets") val changeInOperatingAssets: String?,
    @field:Json(name = "depreciationDepletionAndAmortization") val depreciationDepletionAndAmortization: String?,
    @field:Json(name = "capitalExpenditures") val capitalExpenditures: String?,
    @field:Json(name = "changeInReceivables") val changeInReceivables: String?,
    @field:Json(name = "changeInInventory") val changeInInventory: String?,
    @field:Json(name = "profitLoss") val profitLoss: String?,
    @field:Json(name = "cashflowFromInvestment") val cashflowFromInvestment: String?,
    @field:Json(name = "cashflowFromFinancing") val cashflowFromFinancing: String?,
    @field:Json(name = "proceedsFromRepaymentsOfShortTermDebt") val proceedsFromRepaymentsOfShortTermDebt: String?,
    @field:Json(name = "paymentsForRepurchaseOfCommonStock") val paymentsForRepurchaseOfCommonStock: String?,
    @field:Json(name = "paymentsForRepurchaseOfEquity") val paymentsForRepurchaseOfEquity: String?,
    @field:Json(name = "paymentsForRepurchaseOfPreferredStock") val paymentsForRepurchaseOfPreferredStock: String?,
    @field:Json(name = "dividendPayout") val dividendPayout: String?,
    @field:Json(name = "dividendPayoutCommonStock") val dividendPayoutCommonStock: String?,
    @field:Json(name = "dividendPayoutPreferredStock") val dividendPayoutPreferredStock: String?,
    @field:Json(name = "proceedsFromIssuanceOfCommonStock") val proceedsFromIssuanceOfCommonStock: String?,
    @field:Json(name = "proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet") val proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet: String?,
    @field:Json(name = "proceedsFromIssuanceOfPreferredStock") val proceedsFromIssuanceOfPreferredStock: String?,
    @field:Json(name = "proceedsFromRepurchaseOfEquity") val proceedsFromRepurchaseOfEquity: String?,
    @field:Json(name = "proceedsFromSaleOfTreasuryStock") val proceedsFromSaleOfTreasuryStock: String?,
    @field:Json(name = "changeInCashAndCashEquivalents") val changeInCashAndCashEquivalents: String?,
    @field:Json(name = "changeInExchangeRate") val changeInExchangeRate: String?,
    @field:Json(name = "netIncome") val netIncome: String?
)