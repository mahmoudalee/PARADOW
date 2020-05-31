package com.example.artbot;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artbot.model.SignupRes;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name)
    EditText _userNameText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_pass)
    EditText _passwordText;
    @BindView(R.id.input_cpass)
    EditText _conpasswordText;
    @BindView(R.id.btn_sign)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;


    String name,email,password,Cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        //not needed it initialized in validation method
        // name = _userNameText.getText().toString();
        //        email = _emailText.getText().toString();
        //        password = _passwordText.getText().toString();
        //        Cpassword = _conpasswordText.getText().toString();

        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<SignupRes> call = service.signUp(name, email, password);
        call.enqueue(new Callback<SignupRes>() {
            @Override
            public void onResponse(Call<SignupRes> call, Response<SignupRes> response) {

                progressDialog.dismiss();

                if(response.code()==400) {

                    _signupButton.setEnabled(true);
                    Log.i(TAG+" Response:","Email Duplicated");

//                    Toast.makeText(getBaseContext(), "this email already used"+response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    Gson gson = new GsonBuilder().create();
                    SignupRes mError=new SignupRes();
                    try {
                        mError= gson.fromJson(response.errorBody().string(),SignupRes .class);
                        if(mError.getMessage().getRememberToken().contains("Duplicated"))
                        Toast.makeText(getApplicationContext(), "this email already used", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        // handle failure to read error
                    }
                }
                else
                {
                    onSignupSuccess();
                    Log.i("Response:", "Sign UP Done" + response.body().getMessage().getRememberToken());

                }


            }

            @Override
            public void onFailure(Call<SignupRes> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("Failure:", t.getMessage());
                onSignupFailed();
            }
        });


    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        name = _userNameText.getText().toString();
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        Cpassword = _conpasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _userNameText.setError("at least 3 characters");
            valid = false;
        } else {
            _userNameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 5 ) {
            _passwordText.setError("Password needs to be more than 7 charachters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if (!password.equals(Cpassword)){
            _passwordText.setError("Password and Confirm Password doesn't match");
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}