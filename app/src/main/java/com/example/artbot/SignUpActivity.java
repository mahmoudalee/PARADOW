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

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    EditText _userNameText;
    EditText _emailText;
    EditText _passwordText;
    EditText _conpasswordText;
    Button _signupButton;
    TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        _userNameText = findViewById(R.id.input_name);
        _emailText = findViewById(R.id.input_email);
        _passwordText = findViewById(R.id.input_pass);
        _conpasswordText = findViewById(R.id.input_cpass);
        _signupButton = findViewById(R.id.btn_sign);
        _loginLink = findViewById(R.id.link_login);


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

        onSignupSuccess();

        progressDialog.dismiss();

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

        String name = _userNameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String Cpassword = _conpasswordText.getText().toString();

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