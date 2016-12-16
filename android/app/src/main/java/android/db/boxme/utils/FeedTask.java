package android.db.boxme.utils;

import android.db.boxme.model.GitEvent;
import android.db.boxme.model.User;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Caritor on 12/16/2016.
 */
public class FeedTask extends AsyncTask<Void,Void,List<GitEvent>> {

    private FeedTaskListener mListener;
    private final int mPage;

    public FeedTask(int page , FeedTaskListener listener){
        mPage = page;
        mListener = listener;
    }
    @Override
    protected List<GitEvent> doInBackground(Void... voids) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://49.204.78.3/KSRTC/api/web/")
                .baseUrl("https://api.github.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofit.create(GithubService.class);
        Call<List<GitEvent>> eventCall = service.getEvents( User.getInstance().login , mPage );
        try {
            Response<List<GitEvent>> eventResponse = eventCall.execute();
            List<GitEvent> events = eventResponse.body();

            return events;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<GitEvent> gitEvents) {
        super.onPostExecute(gitEvents);

        if(mListener != null && gitEvents != null ){
            mListener.onSuccess( gitEvents );
        }
    }

    public interface FeedTaskListener extends EventListener{
        public void onSuccess( List<GitEvent> events );
        public void onFailure( );
    }

}
