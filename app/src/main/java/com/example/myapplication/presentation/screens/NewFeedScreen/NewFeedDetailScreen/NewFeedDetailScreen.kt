package com.example.myapplication.presentation.screens.NewFeedScreen.NewFeedDetailScreen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature

@Composable
fun NewFeedDetailScreen(
    newsUrl: String
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        WebViewContent(newsUrl)
    }
}

@Composable
fun WebViewContent(url: String) {
    WebView(modifier = Modifier.fillMaxSize(), url = url)
}

@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String,
) {
    val webView = androidx.compose.ui.platform.LocalContext.current.let {
        WebView(it)
    }

    androidx.compose.runtime.DisposableEffect(url) {
        val settings = webView.settings

        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_AUTO)
        }

        webView.webViewClient = object : WebViewClientCompat() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Add any custom logic when the page finishes loading
            }
        }

        webView.loadUrl(url)

        onDispose {
            // Cleanup
            webView.destroy()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { webView }
    )
}
