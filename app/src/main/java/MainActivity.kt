package com.example.bubblepoplearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the WebView we created in the XML layout
        val webView: WebView = findViewById(R.id.webView)

        // --- CONFIGURE THE BROWSER ENGINE ---
        val webSettings: WebSettings = webView.settings

        // 1. Turn on the game logic
        webSettings.javaScriptEnabled = true

        // 2. Turn on Local Storage (CRITICAL for saving Player Name and XP!)
        webSettings.domStorageEnabled = true

        // 3. Allow sounds and AI Voice to play immediately when tapped
        webSettings.mediaPlaybackRequiresUserGesture = false

        // Keep all clicks and links inside our app, don't open Chrome
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // --- LOAD THE GAME ---
        // Notice how it points to the assets folder we created!
        webView.loadUrl("file:///android_asset/BubblePopGame/BubbleGame.html")

        // Modern way to handle the Back button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    // Disable this callback and let the system handle the back button (usually closing the app)
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}
