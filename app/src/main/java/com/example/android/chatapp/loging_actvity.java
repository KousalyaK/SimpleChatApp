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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class loging_actvity extends AppCompatActivity {

    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loging_actvity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button button = (Button) findViewById(R.id.action_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate login data
                boolean validationError = false;

                StringBuilder logError = new StringBuilder(getResources().getString(R.string.error_intro));

                if(isEmpty(username)){
                    validationError = true;
                    logError.append(getResources().getString(R.string.error_blank_username));
                }
                logError.append(getResources().getString(R.string.error_end));

                if(isEmpty(password)){
                    validationError = true;
                    logError.append(getResources().getString(R.string.error_blank_username));
                }
                logError.append(getResources().getString(R.string.error_end));

                if(validationError){
                    Toast.makeText(loging_actvity.this, logError.toString(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // sign in progress bar

                final ProgressDialog pdlog = new ProgressDialog(loging_actvity.this);
                pdlog.setTitle("Please Wait.");
                pdlog.setMessage("Logging in. Please wait. ");
                pdlog.show();

                // calling that parse login method

                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        pdlog.dismiss();
                        if(e != null){
                            Toast.makeText(loging_actvity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {

                            Intent intent = new Intent(loging_actvity.this, Chat_activity.class);
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
        getMenuInflater().inflate(R.menu.menu_loging_actvity, menu);
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
}
