package com.example.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class CompanyIncomeStatementDto(
    @Json(name = "symbol") val symbol: String,
    @Json(name = "annualReports") val annualReports: List<IncomeStatementAnnualReport>
)

data class IncomeStatementAnnualReport(
    @field:Json(name = "fiscalDateEnding") val fiscalDateEnding : String?,
    @field:Json(name = "reportedCurrency") val reportedCurrency: String?,
    @field:Json(name = "grossProfit")  val grossProfit: String?,
    @field:Json(name = "totalRevenue") val totalRevenue: String?,
    @field:Json(name = "costOfRevenue") val costOfRevenue: String?,
    @field:Json(name = "costofGoodsAndServicesSold") val costofGoodsAndServicesSold: String?,
    @field:Json(name = "operatingIncome") val operatingIncome: String?,
    @field:Json(name = "sellingGeneralAndAdministrative") val sellingGeneralAndAdministrative: String?,
    @field:Json(name = "researchAndDevelopment") val researchAndDevelopment: String?,
    @field:Json(name = "operatingExpenses") val operatingExpenses: String?,
    @field:Json(name = "investmentIncomeNet") val investmentIncomeNet: String?,
    @field:Json(name = "netInterestIncome") val netInterestIncome: String?,
    @field:Json(name = "interestIncome") val interestIncome: String?,
    @field:Json(name = "interestExpense") val interestExpense: String?,
    @field:Json(name = "nonInterestIncome") val nonInterestIncome: String?,
    @field:Json(name = "otherNonOperatingIncome") val otherNonOperatingIncome: String?,
    @field:Json(name = "depreciation") val depreciation: String?,
    @field:Json(name = "depreciationAndAmortization") val depreciationAndAmortization: String?,
    @field:Json(name = "incomeBeforeTax") val incomeBeforeTax: String?,
    @field:Json(name = "incomeTaxExpense") val incomeTaxExpense: String?,
    @field:Json(name = "interestAndDebtExpense") val interestAndDebtExpense: String?,
    @field:Json(name = "netIncomeFromContinuingOperations") val netIncomeFromContinuingOperations: String?,
    @field:Json(name = "comprehensiveIncomeNetOfTax") val comprehensiveIncomeNetOfTax: String?,
    @field:Json(name = "ebit") val ebit: String?,
    @field:Json(name = "ebitda") val ebitda: String?,
    @field:Json(name = "netIncome") val netIncome: String?
)