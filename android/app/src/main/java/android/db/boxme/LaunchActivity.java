package android.db.boxme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LaunchActivity extends AppCompatActivity {

    private View mContentPanel;
    private boolean shouldFinishOnStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);
        mContentPanel = findViewById(R.id.contentPanel);

    }



    @Override
    protected void onResume() {
        super.onResume();

        shouldFinishOnStop = false;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                shouldFinishOnStop = true;

                Intent aIntent = new Intent(LaunchActivity.this , LoginActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LaunchActivity.this, (View)mContentPanel, "content");
                startActivity(aIntent , options.toBundle() );

            }
        }, 2000);

    }

    @Override
    protected void onStop() {
        super.onStop();

        if( shouldFinishOnStop )
            finish();
    }
}
