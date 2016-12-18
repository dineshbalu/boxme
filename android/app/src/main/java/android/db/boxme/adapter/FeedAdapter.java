package android.db.boxme.adapter;

import android.content.Context;
import android.content.Intent;
import android.db.boxme.HomeActivity;
import android.db.boxme.R;
import android.db.boxme.RepositoryDetailActivity;
import android.db.boxme.model.GitEvent;
import android.db.boxme.utils.DateTime;
import android.db.boxme.utils.FeedTask;
import android.db.boxme.utils.GitConstant;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caritor on 12/16/2016.
 */
public class FeedAdapter extends RecyclerView.Adapter {

    private View mContentPanel;
    private int page = 1;
    private boolean isLoading = false;
    private boolean shouldLoadMore = true;

    private final Context mContext;
    private ArrayList<GitEvent> mEvents;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public static final int TYPE_VIEW = 1;
        public static final int TYPE_LOADER = 2;

        public int mType;
        public ImageView mThumbView;
        public TextView mNameView;
        public TextView mDurationView;
        public TextView mMessageView;
        public Button mRepoView;

        public Button mLoadBtn;
        public ProgressBar mProgressBar;

        public ViewHolder(View v , int type) {
            super(v);

            mType = type;

            if( mType == TYPE_VIEW ) {
                mThumbView = (ImageView) v.findViewById(R.id.thumbView);
                mNameView = (TextView) v.findViewById(R.id.nameView);
                mDurationView = (TextView) v.findViewById(R.id.durationView);
                mMessageView = (TextView) v.findViewById(R.id.contentView);
                mRepoView = (Button) v.findViewById(R.id.repoView);

                mRepoView.setOnClickListener( onRepoClicked );

            }
            else{
                mLoadBtn = (Button) v.findViewById(R.id.loadMoreBtn);
                mProgressBar = (ProgressBar) v.findViewById(R.id.progressView);

                mLoadBtn.setOnClickListener( onLoadMoreClicked );
            }
        }


        private View.OnClickListener onRepoClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GitEvent event = (GitEvent)view.getTag();
                Log.v( "Repo" , event.repo.url);

                Intent dIntent = new Intent(mContext, RepositoryDetailActivity.class);
                dIntent.putExtra(GitConstant.EXTRA_REPO_NAME , event.repo.name);
                dIntent.putExtra(GitConstant.EXTRA_REPO_URL , event.repo.url);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation( (HomeActivity)mContext , (View)mContentPanel, "content");
                mContext.startActivity(dIntent , options.toBundle() );
            }
        };

        private View.OnClickListener onLoadMoreClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLoadBtn.setVisibility( View.GONE );
                mProgressBar.setVisibility( View.VISIBLE);

                FeedTask task = new FeedTask( ++page , feedListener );
                task.execute();

                isLoading = true;
            }
        };

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(Context context , View content) {
        mEvents = new ArrayList<GitEvent>();
        mContext = context;
        mContentPanel = content;


        FeedTask task = new FeedTask( page , feedListener );
        task.execute();

        isLoading = true;

    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);

        if( position == mEvents.size() )
            return ViewHolder.TYPE_LOADER;

        return ViewHolder.TYPE_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v;

        if( viewType == ViewHolder.TYPE_VIEW )
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_layout, parent, false);
        else
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_layout, parent, false);

        ViewHolder vh = new ViewHolder(v,viewType);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;

        if( viewHolder.mType == ViewHolder.TYPE_VIEW ) {
            GitEvent event = mEvents.get(position);

            Picasso.with(mContext).load(event.actor.avatar_url).into(viewHolder.mThumbView);
            viewHolder.mNameView.setText(mEvents.get(position).actor.login);
            viewHolder.mDurationView.setText(DateTime.getPrettyTime(mEvents.get(position).created_at));
            viewHolder.mMessageView.setText(mEvents.get(position).getMessage());
            viewHolder.mRepoView.setText(mEvents.get(position).getRepositoryName());
            viewHolder.mRepoView.setTag(event);
        }
        else{
            viewHolder.mLoadBtn.setVisibility( (isLoading)?View.GONE:View.VISIBLE );
            viewHolder.mProgressBar.setVisibility( (isLoading)?View.VISIBLE:View.GONE );
        }

    }

    @Override
    public int getItemCount() {
        return (shouldLoadMore)?mEvents.size()+1:mEvents.size();
    }


    FeedTask.FeedTaskListener feedListener = new FeedTask.FeedTaskListener() {
        @Override
        public void onSuccess(List<GitEvent> events) {

            isLoading = false;

            if( events.size() > 0 )
                mEvents.addAll( events );
            else
                shouldLoadMore = false;

            notifyDataSetChanged();



            Log.v("HomeActivity","List Count : " + events.size());
        }

        @Override
        public void onFailure() {
            Log.v("HomeActivity","Failed : " );
            isLoading = false;
        }
    };


}
