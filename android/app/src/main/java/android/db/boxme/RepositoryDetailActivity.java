package android.db.boxme;

import android.db.boxme.model.GitEvent;
import android.db.boxme.utils.GitConstant;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RepositoryDetailActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String name = getIntent().getStringExtra(GitConstant.EXTRA_REPO_NAME);
        String url = getIntent().getStringExtra(GitConstant.EXTRA_REPO_URL);

        String[] names = name.split("/");

        String title = names[1];

        TextView titleView = (TextView) findViewById(R.id.titleView);
        titleView.setText(title);
        TextView urlView = (TextView) findViewById(R.id.urlView);
        urlView.setText(name);

        mProgressBar = (ProgressBar) findViewById(R.id.progressView);
        mProgressBar.setVisibility( View.VISIBLE );

        WebView webView = (WebView) findViewById(R.id.contentPanel);
        webView.loadUrl( String.format("https://github.com/%s",name) );

        webView.setWebViewClient( new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility( View.VISIBLE );

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mProgressBar.setVisibility( View.GONE );
            }
        });
    }

    public void onBackClicked(View view){
        finish();
    }
}
