package android.db.boxme.adapter;

import android.content.Context;
import android.db.boxme.R;
import android.db.boxme.model.GitEvent;
import android.db.boxme.utils.DateTime;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Caritor on 12/16/2016.
 */
public class FeedAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<GitEvent> mEvents;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mThumbView;
        public TextView mNameView;
        public TextView mDurationView;
        public TextView mMessageView;
        public TextView mRepoView;
        public ViewHolder(View v) {
            super(v);
            mThumbView = (ImageView)v.findViewById(R.id.thumbView);
            mNameView = (TextView)v.findViewById(R.id.nameView);
            mDurationView = (TextView)v.findViewById(R.id.durationView);
            mMessageView = (TextView)v.findViewById(R.id.contentView);
            mRepoView = (TextView)v.findViewById(R.id.repoView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(Context context , List<GitEvent> events) {
        mEvents = events;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;
        GitEvent event = mEvents.get(position);

        Picasso.with( mContext ).load( event.actor.avatar_url ).into( viewHolder.mThumbView );
        viewHolder.mNameView.setText(mEvents.get(position).actor.login);
        viewHolder.mDurationView.setText( DateTime.getPrettyTime(mEvents.get(position).created_at) );
        viewHolder.mMessageView.setText(mEvents.get(position).type);
        viewHolder.mRepoView.setText(mEvents.get(position).repo.name);

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}
