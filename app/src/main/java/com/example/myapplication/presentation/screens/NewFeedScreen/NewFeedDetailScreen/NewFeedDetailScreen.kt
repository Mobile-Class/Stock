package com.example.myapplication.presentation.screens.NewFeedScreen.NewFeedDetailScreen

import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewFeature

@Composable
fun NewFeedDetailScreen(
    navController: NavController,
    newsUrl: String
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colors.
    ) {
        TopAppBar(
            title = { androidx.compose.material.Text("Detail Information") }, // Display ticker in the title
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Log.d("Navigation", "Received newsUrl: $newsUrl")

        Text(text = "Received newsUrl: $newsUrl")
//        WebViewContent(newsUrl)
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
