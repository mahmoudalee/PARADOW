package com.example.artbot;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artbot.model.LoginRes;
import com.example.artbot.model.UserData;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _userNameText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    UserData user = new UserData();
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);


        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        token = preferences.getString("token", null);

//        if (token != null){
//            Intent intent = new Intent(this, MainActivity.class);
////
////            intent.putExtra("user",user);
////
//            startActivity(intent);
//            finish();
//        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                login();

            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                 Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });




    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        else if (!isNetworkConnected()) {
            Toast.makeText(LoginActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _userNameText.getText().toString();
        String password = _passwordText.getText().toString();


        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<LoginRes> call = service.login(email , password);
        call.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {

                SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

                preferences.edit().putString("token", response.body().getMessage().getRememberToken()).apply();


                Log.i("Response:",response.body().getMessage().getRememberToken());

                user.setName(response.body().getMessage().getUserData().getName());
                user.setPhoto(response.body().getMessage().getUserData().getPhoto());
                user.setFavProduct(response.body().getMessage().getUserData().getFavProduct());
                user.setId(response.body().getMessage().getUserData().getId());

                progressDialog.dismiss();
                onLoginSuccess();
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("Failure:",t.getMessage());
                onLoginFailed();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(LoginActivity.this,"Signed Up, You are welcome to login",Toast.LENGTH_SHORT).show();
            }
        }
    }


    long backLastPressed = 0;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backLastPressed < 2000) {
            // TODO: Register double-tapped BACK button, put your "exit" code here
            super.onBackPressed();
            backLastPressed = 0;
            return;
        }
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        backLastPressed = System.currentTimeMillis();
        // Otherwise, ignore this BACK press
    }

    public void onLoginSuccess() {

        _loginButton.setEnabled(true);

        Intent intent= new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed, please check your Data", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _userNameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _userNameText.setError("enter a valid userName address");
            valid = false;
        } else {
            _userNameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 5) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected() ;
    }


//    private boolean isOnTheInternet() {
//        try {
//            URLConnection urlConnection = new URL("https://www.google.com/").openConnection();
//            urlConnection.setConnectTimeout(400);
//            urlConnection.connect();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//    public boolean isInternetAvailable() {
//        try {
//            InetAddress ipAddr = InetAddress.getByName("google.com");
//            //You can replace it with your name
//            return true;
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
