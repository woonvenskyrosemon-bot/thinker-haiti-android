package ht.thinkerhaiti.app

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.CookieManager
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView

    // IMPORTANT : remplace cette URL par l'adresse HTTPS réelle de ton site hébergé.
    private val appUrl = "https://REMPLACEZ-PAR-VOTRE-DOMAINE/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(201, 31, 50)
        window.navigationBarColor = android.graphics.Color.BLACK
        window.decorView.systemUiVisibility = 0

        webView = WebView(this)
        setContentView(webView)
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, false)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = false
        webView.settings.allowContentAccess = false
        webView.settings.javaScriptCanOpenWindowsAutomatically = false
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val uri = request.url
                val host = uri.host ?: return true
                val appHost = Uri.parse(appUrl).host
                if (host == appHost) return false
                if (uri.scheme == "https" || uri.scheme == "http" || uri.scheme == "mailto" || uri.scheme == "tel") {
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    return true
                }
                return true
            }
        }

        if (appUrl.contains("REMPLACEZ-PAR-VOTRE-DOMAINE")) {
            Toast.makeText(this, "Configure d'abord l'adresse HTTPS du site dans MainActivity.kt.", Toast.LENGTH_LONG).show()
            webView.loadData(
                "<html><meta name='viewport' content='width=device-width, initial-scale=1'><body style='font-family:sans-serif;padding:24px'><h2 style='color:#c91f32'>THINKER HAÏTI</h2><p>Cette application est prête à être configurée.</p><p>Héberge d'abord le site, puis remplace l'adresse appUrl dans MainActivity.kt par ton URL HTTPS.</p></body></html>",
                "text/html", "UTF-8"
            )
        } else {
            webView.loadUrl(appUrl)
        }
    }

    @Deprecated("Deprecated in Android")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}
