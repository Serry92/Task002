package android.serry.task022.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.serry.task022.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class TermsFragment extends Fragment {
    private static TermsFragment instanceTermsFragment;
    WebView webView;

    public static synchronized TermsFragment getInstance() {
        if (instanceTermsFragment == null) {
            instanceTermsFragment = new TermsFragment();
        }
        return new TermsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = view.findViewById(R.id.wb_terms_url);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(getResources().getString(R.string.terms_url));
        return view;

    }
}
