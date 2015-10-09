package com.example.android.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUPActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.passwordAgain);


        Button signup = (Button) findViewById(R.id.action_button);

        findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StringBuilder logError = new StringBuilder(getResources().getText(R.string.error_intro));
                boolean validator = false;


                if (isEmpty(username)) {
                    validator = true;
                    logError.append(getResources().getString(R.string.error_blank_username));
                }
                logError.append(getResources().getString(R.string.error_end));
                if (isEmpty(password)) {
                    validator = true;
                    logError.append(getResources().getString(R.string.error_blank_password));
                }
                if (isEmpty(password2)) {
                    validator = true;
                    logError.append(getResources().getString(R.string.error_blank_password));
                }

                if (!isMatching(password, password2)) {
                    if (validator) {
                        logError.append(getResources().getString(R.string.error_join));
                    }
                    validator = true;
                    logError.append(getResources().getString(
                            R.string.error_mismatched_passwords));
                }

                logError.append(getResources().getString(R.string.error_end));

                if (validator) {
                    Toast.makeText(SignUPActivity.this, logError.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final ProgressDialog pdlog = new ProgressDialog(SignUPActivity.this);

                pdlog.setTitle("Siging Up");
                pdlog.setMessage("Signing up, Plaease wait.");
                pdlog.show();

                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {
                        pdlog.dismiss();
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(SignUPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            // Start an intent for the dispatch activity
                            Intent intent = new Intent(SignUPActivity.this, Chat_activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
                }
            });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_u, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    private boolean isMatching(EditText editText, EditText editText1){
        if (editText.getText().toString().equals(editText1.getText().toString())){
            return true;
        } else {
            return false;
        }
    }
}
