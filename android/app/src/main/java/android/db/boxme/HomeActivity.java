package android.db.boxme;

import android.db.boxme.adapter.FeedAdapter;
import android.db.boxme.model.GitEvent;
import android.db.boxme.utils.FeedTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private int page = 1;
    private RecyclerView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (RecyclerView) findViewById(R.id.listView );

        mListView.setLayoutManager( new LinearLayoutManager(this) );

        FeedTask task = new FeedTask( page , feedListener );
        task.execute();

    }


    FeedTask.FeedTaskListener feedListener = new FeedTask.FeedTaskListener() {
        @Override
        public void onSuccess(List<GitEvent> events) {

            mListView.setAdapter( new FeedAdapter( HomeActivity.this , events) );

            Log.v("HomeActivity","List Count : " + events.size());
        }

        @Override
        public void onFailure() {
            Log.v("HomeActivity","Failed : " );

        }
    };
}
