package com.example.administrator.androidtojs;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

public class MainActivity extends AppCompatActivity {

    private AgentWeb mAgentWeb;
    private WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }
    };

    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
            if (newProgress == 100) {
                //在加载完成后 给js传参数(通过方法 传参数)
                mAgentWeb.getJsAccessEntrace().quickCallJs("receiveMsg", "Hello From Android");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((FrameLayout) findViewById(R.id.content), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go("http://192.168.0.105:8080/#/");

        //给js 添加java对象 让js 能通过 window.android.showInfoFromJs(msg) 调用Android的方法
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new JsInterface(this));

    }
}
