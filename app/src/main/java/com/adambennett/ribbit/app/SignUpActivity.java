package com.adambennett.ribbit.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends Activity {

    protected EditText mUsername, mPassword, mEmail;
    protected Button mSignUpButton, mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hide the actionbar, after call to setContentView
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        mUsername = (EditText) findViewById(R.id.etUsernameField);
        mPassword = (EditText) findViewById(R.id.etPasswordField);
        mEmail = (EditText) findViewById(R.id.etEmailField);

        mCancelButton = (Button) findViewById(R.id.bCancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSignUpButton = (Button) findViewById(R.id.bSignUp);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(getString(R.string.signup_error_message));
                    builder.setTitle(getString(R.string.signup_error_title));
                    builder.setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    //create new user!
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);
                            if (e == null) {
                                //Success!
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle(getString(R.string.signup_error_title));
                                builder.setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });

    }
}
