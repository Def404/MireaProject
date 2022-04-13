package ru.mirea.baskakov.mireaproject.ui.webview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.baskakov.mireaproject.R;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private EditText searchInputText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_web_view, container, false);
        view.findViewById(R.id.search_btn).setOnClickListener(this::searchBtnOnClick);

        searchInputText = (EditText) view.findViewById(R.id.editText);

        webView = (WebView) view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com/");
        return view;
    }

    private void searchBtnOnClick(View view){

        String searchText = "http://"+ searchInputText.getText().toString();
        webView.loadUrl(searchText);
    }
}
