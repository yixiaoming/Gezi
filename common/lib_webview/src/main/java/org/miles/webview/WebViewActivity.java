package org.miles.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lib_webview.R;

import java.nio.file.FileAlreadyExistsException;

public class WebViewActivity extends AppCompatActivity {

    public static final String OPEN_URL_ACTION = "org.miles.webview.open";
    public static final String BUNDLE_KEY_URL = "url";

    private WebView mWebview;

    public static void openWebviewWithUrl(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(OPEN_URL_ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(BUNDLE_KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        mWebview = findViewById(R.id.webview);
        if (getIntent() != null && getIntent().getExtras() != null) {
            String url = getIntent().getExtras().getString(BUNDLE_KEY_URL);
            if (!TextUtils.isEmpty(url)) {
                mWebview.loadUrl(url);
            }
        }
    }
}
