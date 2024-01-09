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
import com.example.myapplication.ui.theme.DarkGreen
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
            PillarData("5YR P/E Ratio < 22.5", "43.98", "-1,95%", false, "The 5-year P/E (Price to Earnings) Ratio is a significant financial metric used to evaluate the valuation of a company. This ratio is calculated by dividing the current market price of a company's stock by its earnings per share (EPS) over the past five years. A P/E ratio less than 22.5 is often considered as a benchmark for identifying stocks that might be undervalued.\n" +
                    "\n" +
                    "This metric is important for several reasons:\n" +
                    "\n" +
                    "Long-term Perspective: The 5-year period offers a more comprehensive view of a company's performance, smoothing out short-term anomalies and market fluctuations. This gives investors a better sense of the company's long-term profitability.\n" +
                    "\n" +
                    "Valuation Indicator: A lower P/E ratio might suggest that the stock is undervalued relative to its earnings. This could be a signal for potential investment opportunities, as the stock might be trading at a price lower than what its earnings justify.\n" +
                    "\n" +
                    "Comparative Analysis: Investors often use the 5-year P/E ratio to compare companies within the same industry. A company with a P/E ratio significantly lower than its industry average might be undervalued, or it might indicate underlying issues that need further investigation.\n" +
                    "\n" +
                    "Market Sentiment: This ratio can also reflect the market's sentiment towards a company. A lower ratio might indicate skepticism about future growth prospects, while a higher ratio could suggest market confidence in the company's future earnings potential.\n" +
                    "\n" +
                    "In summary, the 5-year P/E Ratio is a valuable tool for investors seeking to gauge a company's relative value and long-term profitability, making it a key pillar in the assessment of a company's investment potential.\n" +
                    "\n" +
                    "User\n" +
                    "Do it for 5YR ROIC > 9%\n" +
                    "ChatGPT\n" +
                    "The 5-year Return on Invested Capital (ROIC) greater than 9% is a crucial financial metric for evaluating a company's efficiency and profitability in terms of capital allocation. ROIC measures how well a company generates earnings from its invested capital, which includes debt and equity. Here's why a 5-year ROIC of over 9% is significant:\n" +
                    "\n" +
                    "Long-term Performance Assessment: A 5-year period allows for a more comprehensive evaluation of a company's performance, mitigating the impact of short-term market fluctuations and operational anomalies. This longer timeframe provides a clearer picture of the company's efficiency in using capital over a substantial period.\n" +
                    "\n" +
                    "Efficient Capital Utilization: A higher ROIC indicates that a company is efficiently using its capital to generate profits. Specifically, a 5-year ROIC greater than 9% suggests that the company has consistently been good at turning its capital investments into profitable ventures, which is a sign of strong management and operational effectiveness.\n" +
                    "\n" +
                    "Investment Attractiveness: Investors often seek companies with high ROIC because it implies that the company is capable of generating higher returns on additional capital. This makes such companies attractive investment opportunities, as they are likely to provide better returns on shareholder investments.\n" +
                    "\n" +
                    "Benchmarking and Industry Comparison: Comparing the ROIC of companies within the same industry can reveal which ones are managing their capital more efficiently. A company with a 5-year ROIC significantly higher than its peers indicates superior management and operational effectiveness within its industry.\n" +
                    "\n" +
                    "Indication of Sustainable Growth: A consistently high ROIC over a 5-year period suggests that the company has a sustainable competitive advantage. This could be due to superior business practices, stronger market positioning, or innovative products/services, which can lead to sustainable growth and profitability in the long term.\n" +
                    "\n" +
                    "In conclusion, a 5-year ROIC greater than 9% is an important indicator of a company's ability to effectively use its capital to generate profits. It is a key metric for investors and analysts when assessing a company's financial health, management effectiveness, and long-term growth potential."),
            PillarData("5YR ROIC > 9%", "94%", "+1,02%", true, "The 5-year Return on Invested Capital (ROIC) greater than 9% is a crucial financial metric for evaluating a company's efficiency and profitability in terms of capital allocation. ROIC measures how well a company generates earnings from its invested capital, which includes debt and equity. Here's why a 5-year ROIC of over 9% is significant:\n" +
                    "\n" +
                    "Long-term Performance Assessment: A 5-year period allows for a more comprehensive evaluation of a company's performance, mitigating the impact of short-term market fluctuations and operational anomalies. This longer timeframe provides a clearer picture of the company's efficiency in using capital over a substantial period.\n" +
                    "\n" +
                    "Efficient Capital Utilization: A higher ROIC indicates that a company is efficiently using its capital to generate profits. Specifically, a 5-year ROIC greater than 9% suggests that the company has consistently been good at turning its capital investments into profitable ventures, which is a sign of strong management and operational effectiveness.\n" +
                    "\n" +
                    "Investment Attractiveness: Investors often seek companies with high ROIC because it implies that the company is capable of generating higher returns on additional capital. This makes such companies attractive investment opportunities, as they are likely to provide better returns on shareholder investments.\n" +
                    "\n" +
                    "Benchmarking and Industry Comparison: Comparing the ROIC of companies within the same industry can reveal which ones are managing their capital more efficiently. A company with a 5-year ROIC significantly higher than its peers indicates superior management and operational effectiveness within its industry.\n" +
                    "\n" +
                    "Indication of Sustainable Growth: A consistently high ROIC over a 5-year period suggests that the company has a sustainable competitive advantage. This could be due to superior business practices, stronger market positioning, or innovative products/services, which can lead to sustainable growth and profitability in the long term.\n" +
                    "\n" +
                    "In conclusion, a 5-year ROIC greater than 9% is an important indicator of a company's ability to effectively use its capital to generate profits. It is a key metric for investors and analysts when assessing a company's financial health, management effectiveness, and long-term growth potential."),
            PillarData("Shares Outstanding Decreasing", "32.45%", "-1,27%", false, "When a company's \"Shares Outstanding\" is decreasing, it indicates that the company is reducing the total number of its shares available in the market. This can happen for several reasons, and it typically has important implications for the company and its shareholders:\n" +
                    "\n" +
                    "Share Buybacks: One common reason for a decrease in shares outstanding is a share buyback (or repurchase) program. In a buyback, a company purchases its own shares from the market, reducing the number of shares outstanding. This often leads to an increase in the earnings per share (EPS) since the net income is now spread over a smaller number of shares.\n" +
                    "\n" +
                    "Capital Restructuring: Companies might reduce their shares outstanding as part of a broader capital restructuring strategy. This could involve consolidating debt, optimizing their equity structure, or preparing for significant corporate changes like mergers or acquisitions.\n" +
                    "\n" +
                    "Increased Shareholder Value: A reduction in shares outstanding can benefit existing shareholders by increasing their ownership stake in the company. Since there are fewer shares available, each share represents a larger fraction of ownership in the company.\n" +
                    "\n" +
                    "Signal of Financial Strength: Engaging in buybacks can be seen as a sign that a company's management believes the stock is undervalued. It can also indicate that the company has sufficient cash reserves, suggesting financial robustness.\n" +
                    "\n" +
                    "Impact on Stock Price: Reducing the number of shares outstanding can lead to an increase in the stock price, partly due to the improved EPS and also due to the perception of financial strength and confidence by the company in its own value.\n" +
                    "\n" +
                    "Tax Efficiency: For shareholders, buybacks can be more tax-efficient than dividends as a method of returning cash. In many jurisdictions, capital gains from stock price increases are taxed more favorably than dividend income.\n" +
                    "\n" +
                    "Potential for Market Misinterpretation: While buybacks can be positive, they may also be viewed critically if it appears that a company is trying to artificially inflate stock prices or if the buyback is funded through debt.\n" +
                    "\n" +
                    "Voting Power Concentration: As shares outstanding decrease, the voting power of each share increases. This can lead to a concentration of voting power among fewer shareholders, potentially impacting corporate governance.\n" +
                    "\n" +
                    "In summary, a decrease in a company's shares outstanding usually indicates a buyback of shares and can be a sign of financial strength and a commitment to enhancing shareholder value. However, the context and manner in which this decrease occurs should be carefully considered to understand its full implications."),
            PillarData("Cash Flow Growth 5 Yr", "814.3M", "+1,02%", true, "\"Cash Flow Growth 5yr\" refers to the increase in a company's cash flow over a five-year period. This metric is significant for several reasons:\n" +
                    "\n" +
                    "Financial Health Indicator: Cash flow, which represents the net amount of cash and cash-equivalents being transferred into and out of a business, is a critical measure of a company's financial health. Growth in cash flow over a five-year period indicates improving financial health and stability.\n" +
                    "\n" +
                    "Operational Success: Increasing cash flow often reflects operational success. It could mean that the company is generating more revenue, efficiently managing its expenses, or both. A steady growth in cash flow suggests that the company is effectively converting its sales or revenue into cash, which is crucial for operational sustainability.\n" +
                    "\n" +
                    "Investment and Expansion Potential: A positive trend in cash flow growth provides the company with more opportunities for investment and expansion without the need for external financing. Companies with growing cash flows are better positioned to invest in new projects, research and development, and expansion into new markets.\n" +
                    "\n" +
                    "Debt Management: Improved cash flow enables a company to manage its debts more effectively. Companies with growing cash flows are more capable of paying down debt, reducing interest costs, and maintaining a healthy debt-to-equity ratio.\n" +
                    "\n" +
                    "Shareholder Value: For shareholders, cash flow growth can translate into increased shareholder value. Companies with increasing cash flows have more resources to pay dividends, buy back shares, or reinvest in the business, all of which can contribute to higher stock prices.\n" +
                    "\n" +
                    "Risk Mitigation: Consistent growth in cash flow over a five-year period can also indicate a lower financial risk for the company. It suggests that the company is less likely to face liquidity issues and can better withstand economic downturns.\n" +
                    "\n" +
                    "Benchmarking Performance: When compared with industry peers, cash flow growth can provide insights into a company's performance relative to its competitors. Higher cash flow growth can indicate a competitive advantage in operational efficiency and financial management.\n" +
                    "\n" +
                    "In summary, \"Cash Flow Growth 5yr\" is a vital metric for assessing a company's financial strength, operational efficiency, investment potential, and overall health. Sustained growth in cash flow is often a key indicator of a successful and well-managed company.\n" +
                    "\n" +
                    "\n"),
            PillarData("5YR Price to Free Cash < 20", "28.58", "-1,27%", false, "\n" +
                    "The \"5YR Price to Free Cash Flow < 20\" is a financial metric that compares a company's current stock price to its average free cash flow over the past five years, specifically focusing on scenarios where this ratio is less than 20. This ratio is crucial for several reasons:\n" +
                    "\n" +
                    "Valuation Measure: This ratio is a measure of a company's valuation, indicating how much investors are willing to pay for each dollar of free cash flow generated by the company. A ratio of less than 20 suggests that the stock may be reasonably valued or potentially undervalued relative to its free cash flow generation capability.\n" +
                    "\n" +
                    "Investment Attractiveness: Investors often use the Price to Free Cash Flow ratio to identify investment opportunities. A ratio under 20 can be attractive as it implies that the company's stock is priced reasonably relative to the cash it produces. It's particularly appealing to value investors looking for stocks that might be undervalued by the market.\n" +
                    "\n" +
                    "Comparative Analysis: By comparing the 5-year average free cash flow to the current stock price, investors can gauge the company's performance relative to its market valuation over a significant period. This helps in smoothing out short-term fluctuations in free cash flow and provides a more stable view of the company's valuation.\n" +
                    "\n" +
                    "Financial Health Indicator: Free cash flow is an important indicator of a company's financial health, representing the cash a company generates after accounting for capital expenditures. A lower Price to Free Cash Flow ratio suggests that the company is efficiently generating more cash than what is currently reflected in the stock price.\n" +
                    "\n" +
                    "Market Sentiment: This ratio can also reflect the market's sentiment towards the company. A ratio below 20 might indicate that the market has not fully recognized the company's value in terms of its cash-generating ability, presenting a potential opportunity for investors.\n" +
                    "\n" +
                    "Risk Assessment: Generally, a lower Price to Free Cash Flow ratio can indicate lower investment risk, as it suggests that the company is not overvalued and has a solid cash flow foundation.\n" +
                    "\n" +
                    "Liquidity and Solvency Insights: Since free cash flow is crucial for maintaining liquidity and solvency, a favorable Price to Free Cash Flow ratio can indicate that the company is in a good position to meet its financial obligations, reinvest in its business, and return value to shareholders.\n" +
                    "\n" +
                    "In summary, the \"5YR Price to Free Cash Flow < 20\" ratio is a significant tool for investors and analysts in assessing a company's stock valuation, financial health, and investment potential. A ratio below 20 often indicates that the company's stock might be undervalued relative to its ability to generate free cash flow, making it an important metric for investment decisions."),
            PillarData("Net Income Growth 5Yr", "527.4M", "+1,02%", true, "\"Net Income Growth 5 YR\" is a financial metric that measures the percentage increase in a company's net income over a five-year period. This metric is vital for understanding a company's profitability and financial health for several reasons:\n" +
                    "\n" +
                    "Profitability Assessment: Net income, which is the profit after all expenses and taxes have been deducted from total revenue, is a key indicator of a company's profitability. A positive net income growth over five years demonstrates that the company is not only making money but is also increasing its profitability over time.\n" +
                    "\n" +
                    "Operational Efficiency: Growth in net income can indicate improvements in operational efficiency. This could be due to cost reductions, more effective management of resources, or successful strategic initiatives. It shows the company's ability to manage its expenses and operations effectively.\n" +
                    "\n" +
                    "Financial Health and Stability: Consistent growth in net income over a five-year span signals strong financial health and stability. It suggests that the company is capable of sustaining and growing its profits, which is crucial for long-term survival and success.\n" +
                    "\n" +
                    "Investor Confidence and Attractiveness: Investors often look for companies with a steady increase in net income as it implies potential for future growth and the likelihood of receiving dividends. Sustained growth in net income is a strong signal for investors about the company's financial robustness.\n" +
                    "\n" +
                    "Benchmarking Against Industry Peers: When compared with industry averages or peers, net income growth can provide insights into how well the company is performing in its sector. Companies with higher net income growth rates compared to their peers are often seen as market leaders or more efficient operators.\n" +
                    "\n" +
                    "Future Growth Prospects: A history of increasing net income is often seen as an indicator of strong future prospects. It suggests that the company has found successful strategies to grow its earnings, which might continue to drive growth in the future.\n" +
                    "\n" +
                    "In conclusion, \"Net Income Growth 5 YR\" is an important metric for evaluating a company's financial performance. It provides a clear picture of the company's ability to increase its earnings over time, reflecting its operational efficiency, financial stability, and overall health. This makes it a critical factor for investors, analysts, and stakeholders in assessing the company's long-term potential and investment value."),
            PillarData("LTL/ 5Yr FCF < 5", "7.2", "-1,4%", false, "\"LTL/5YR FCF < 5\" is a financial ratio that compares a company's Long-Term Liabilities (LTL) to its Five-Year Average Free Cash Flow (5YR FCF), with a specific focus on situations where this ratio is less than 5. This metric provides valuable insights into a company's financial health and operational efficiency:\n" +
                    "\n" +
                    "Debt Management: Long-Term Liabilities (LTL) represent the debt and other financial obligations a company must pay over a period longer than one year. A lower ratio indicates that the company has a manageable level of long-term debt relative to its cash generation capabilities.\n" +
                    "\n" +
                    "Cash Flow Health: Free Cash Flow (FCF) is the cash a company generates after accounting for cash outflows to support operations and maintain its capital assets. A strong FCF is indicative of a company's ability to generate surplus cash, which is crucial for growth, debt repayment, dividend distribution, and stock buybacks.\n" +
                    "\n" +
                    "Financial Stability and Solvency: The ratio \"LTL/5YR FCF < 5\" suggests that the company's long-term liabilities are significantly lower than its average free cash flow over a five-year period. This implies that the company is in a good position to meet its long-term obligations without straining its financial resources, indicating financial stability and solvency.\n" +
                    "\n" +
                    "Investment Attractiveness: For investors, this ratio can be a key indicator of a company's investment potential. A lower ratio is often preferred as it implies that the company is not overly leveraged and has a strong cash flow position to support its operations and growth initiatives.\n" +
                    "\n" +
                    "Operational Efficiency: This ratio also reflects the company's operational efficiency in terms of its ability to manage debt and generate cash. Efficient operations typically result in higher free cash flow, which helps in maintaining a lower ratio.\n" +
                    "\n" +
                    "Risk Assessment: From a risk management perspective, a company with a low \"LTL/5YR FCF\" ratio is generally considered to be at a lower risk of financial distress. This is because the company has sufficient cash flow to cover its long-term liabilities, reducing the likelihood of liquidity issues.\n" +
                    "\n" +
                    "Strategic Flexibility: A company with a healthy balance between its long-term liabilities and cash flow has more strategic flexibility. It can invest in growth opportunities, weather economic downturns more effectively, and make strategic decisions without being overly constrained by its debt obligations.\n" +
                    "\n" +
                    "In summary, the \"LTL/5YR FCF < 5\" ratio is a useful metric for evaluating a company's debt management, cash flow health, financial stability, and overall risk profile. A ratio below 5 typically indicates that the company is in a strong position to manage its long-term liabilities with its generated cash flow, which is a positive sign for investors and stakeholders."),
            PillarData("Revenue Growth 5Yr", "2.21B", "+1,02%", true, "\n" +
                    "\"Revenue Growth 5YR\" refers to the percentage increase in a company's revenue over a five-year period. This metric is crucial in assessing a company's financial health and long-term viability for several reasons:\n" +
                    "\n" +
                    "Indicator of Market Position and Demand: Sustained revenue growth over five years typically indicates a strong market position and growing demand for the company's products or services. It suggests that the company is successfully attracting and retaining customers, expanding its market share, or both.\n" +
                    "\n" +
                    "Business Expansion and Scalability: Consistent revenue growth is often a sign of successful business expansion. This could be through entering new markets, launching new products, or increasing sales in existing channels. It showcases the company's scalability and its ability to adapt to market changes or customer needs.\n" +
                    "\n" +
                    "Investor Attractiveness: From an investment perspective, companies showing a steady increase in revenue over a five-year period are often viewed as attractive investment opportunities. This is because consistent revenue growth can be a predictor of future profitability and financial stability.\n" +
                    "\n" +
                    "Comparison with Industry Peers: Evaluating a company's 5-year revenue growth in the context of its industry can provide insights into its performance relative to peers. Companies outpacing their industry average in revenue growth are typically seen as industry leaders or emerging players with significant potential.\n" +
                    "\n" +
                    "Potential for Future Growth: A track record of revenue growth over a longer period, like five years, may indicate a sustainable business model and the potential for future growth. This is particularly important for long-term investors who are looking for companies with the potential to deliver returns over an extended period.\n" +
                    "\n" +
                    "Impact of Strategic Decisions: This metric can also reflect the impact of strategic decisions made by the company's management. For instance, successful product launches, acquisitions, or market expansions should positively impact revenue growth, indicating effective leadership and strategic planning.\n" +
                    "\n" +
                    "In summary, \"Revenue Growth 5YR\" is a key metric for evaluating a company's growth trajectory, market position, and overall health. It provides valuable insights into how well the company is expanding its business and generating increasing sales, making it a critical factor for investors and analysts in assessing a company's future prospects.\n" +
                    "\n" +
                    "User\n" +
                    "Do it for Net Income Growth 5 YR\n" +
                    "ChatGPT\n" +
                    "\"Net Income Growth 5 YR\" is a financial metric that measures the percentage increase in a company's net income over a five-year period. This metric is vital for understanding a company's profitability and financial health for several reasons:\n" +
                    "\n" +
                    "Profitability Assessment: Net income, which is the profit after all expenses and taxes have been deducted from total revenue, is a key indicator of a company's profitability. A positive net income growth over five years demonstrates that the company is not only making money but is also increasing its profitability over time.\n" +
                    "\n" +
                    "Operational Efficiency: Growth in net income can indicate improvements in operational efficiency. This could be due to cost reductions, more effective management of resources, or successful strategic initiatives. It shows the company's ability to manage its expenses and operations effectively.\n" +
                    "\n" +
                    "Financial Health and Stability: Consistent growth in net income over a five-year span signals strong financial health and stability. It suggests that the company is capable of sustaining and growing its profits, which is crucial for long-term survival and success.\n" +
                    "\n" +
                    "Investor Confidence and Attractiveness: Investors often look for companies with a steady increase in net income as it implies potential for future growth and the likelihood of receiving dividends. Sustained growth in net income is a strong signal for investors about the company's financial robustness.\n" +
                    "\n" +
                    "Benchmarking Against Industry Peers: When compared with industry averages or peers, net income growth can provide insights into how well the company is performing in its sector. Companies with higher net income growth rates compared to their peers are often seen as market leaders or more efficient operators.\n" +
                    "\n" +
                    "Future Growth Prospects: A history of increasing net income is often seen as an indicator of strong future prospects. It suggests that the company has found successful strategies to grow its earnings, which might continue to drive growth in the future.\n" +
                    "\n" +
                    "In conclusion, \"Net Income Growth 5 YR\" is an important metric for evaluating a company's financial performance. It provides a clear picture of the company's ability to increase its earnings over time, reflecting its operational efficiency, financial stability, and overall health. This makes it a critical factor for investors, analysts, and stakeholders in assessing the company's long-term potential and investment value.")
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
    val value: String,
    val change: String,
    val isPositive: Boolean,
    val description: String
)