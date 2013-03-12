package com.skplanet.skp1;

import com.parse.Parse;
import com.parse.PushService;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	
	WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_main);
        
        Parse.initialize(this, "jTBHKQYyGhv0EEGGfGyg1WAwKBbAq3sY55ieHUPR", "W6JXWlN2NBrynV3OulLuFPVhOZbYWbDKCe1mStnm"); 
        PushService.subscribe(this, "skp", MainActivity.class);
        
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true); //자바 스크립트 enable
        webView.setVerticalScrollbarOverlay(true);
        webView.loadUrl("http://skp.tokabout.com");
        webView.setWebViewClient(new SkpWebViewClient());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    // 기기 back 버튼 인식
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
    		webView.goBack();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    // 웹뷰 내부 url 인식?
    private class SkpWebViewClient extends WebViewClient {
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		view.loadUrl(url);
    		return true;
    	}
    }
    
    
    
}
