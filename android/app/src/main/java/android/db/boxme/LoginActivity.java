package android.db.boxme;

import android.annotation.TargetApi;
import android.content.Intent;
import android.db.boxme.model.User;
import android.db.boxme.utils.GithubService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mContentPanel;

    private boolean shouldFinishOnStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailView.setText("dineshbalu");

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("Mala@vel&db16");

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        mProgressView.setVisibility(View.GONE);
        mContentPanel = findViewById(R.id.contentPanel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        shouldFinishOnStop = false;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) ) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
//        else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        finish();
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if( show )
            mProgressView.setVisibility(View.VISIBLE);
        else
            mProgressView.setVisibility(View.GONE);

    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, User> {

        private final String mEmail;
        private final String mPassword;

//        public class LoginFormRequest{
//            LoginForm LoginForm;
//
//            public LoginFormRequest( String name , String pass ){
//                LoginForm = new LoginForm( name , pass );
//            }
//        }
//
//        public class LoginForm{
//            public String username;
//            public String password;
//            public LoginForm( String name , String pass ){
//                username = name;
//                password = pass;
//            }
//        }
//
//        public class TestUser{
//            String status;//": "success",
//            int user_id;//": 1,
//            public String name;//": "Dayanand3  Salagare",
//            String access_token;//": "mk-7e0J_XblYt46kumHoUrCpLeVPH1q4"
//        }

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected User doInBackground(Void... params) {

            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://49.204.78.3/KSRTC/api/web/")
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            String basicAuth = "Basic " + Base64.encodeToString(String.format("%s:%s", mEmail, mPassword).getBytes(), Base64.NO_WRAP);

            GithubService service = retrofit.create(GithubService.class);
            Call<User> userCall = service.authenticateUser( basicAuth );
            try {
                Response<User> userResponse = userCall.execute();
                User user = userResponse.body();
                                Log.v("Login","Response Name:\n"+ user.getName());
                return user;

            } catch (IOException e) {
                e.printStackTrace();
            }

//            Call<TestUser> call = service.login( new LoginFormRequest("dayanand","123456") );
//
//            Response<TestUser> response;
//            try {
//                response = call.execute();
//
////                TestUser[] resa = response.body();
//
//                Log.v("Login","Response Name:\n"+ response.body().name);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                Log.v("Login","Response Error:\n"+call.toString());
//            }

            // TODO: register the new account here.
            return null;
        }

        @Override
        protected void onPostExecute( User user ) {
            mAuthTask = null;
            showProgress(false);

            User.registerInstance( user );

            Intent aIntent = new Intent(LoginActivity.this , HomeActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(LoginActivity.this, (View)mContentPanel, "content");
            startActivity(aIntent , options.toBundle() );

            shouldFinishOnStop = true;

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

