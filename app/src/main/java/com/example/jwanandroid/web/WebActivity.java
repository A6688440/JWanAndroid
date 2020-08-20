package com.example.jwanandroid.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;

import com.example.jwanandroid.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {


    private ActivityWebBinding binding;
    private String WebViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        WebViewUrl=intent.getStringExtra("WebViewUrl");


        binding.mWebView.getSettings().setJavaScriptEnabled(true);
        binding.mWebView.setWebChromeClient(new WebChromeClient());
        binding.mWebView.loadUrl(WebViewUrl);
    }


}
