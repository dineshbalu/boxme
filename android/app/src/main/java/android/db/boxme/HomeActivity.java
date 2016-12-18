package android.db.boxme;

import android.annotation.TargetApi;
import android.db.boxme.adapter.FeedAdapter;
import android.db.boxme.model.GitEvent;
import android.db.boxme.utils.EndlessRecyclerOnScrollListener;
import android.db.boxme.utils.FeedTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private View mContentPanel;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContentPanel = findViewById(R.id.contentPanel);


        mListView = (RecyclerView) findViewById(R.id.listView );

        mListView.setLayoutManager( new LinearLayoutManager(this) );

        mListView.setAdapter( new FeedAdapter( HomeActivity.this , mContentPanel ) );


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
