package androidtown.org.moblieteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class StoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        val webView : WebView = findViewById(R.id.storeWebView)
        webView.loadUrl("https://www.coupang.com/")
    }
}