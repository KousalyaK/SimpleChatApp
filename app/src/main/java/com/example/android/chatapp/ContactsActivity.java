package com.example.android.chatapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends ListActivity {

    List<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);

        /*ParseQuery<User> parseQuery = new ParseQuery<User>("User");

        parseQuery.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> objects, ParseException e) {
                if (e != null){
                    Toast.makeText(ContactsActivity.this, "Error " + e, Toast.LENGTH_SHORT).show();
                }
                for (User objuser : objects){
                    User newuser = new User();
                    newuser.setUsername(objuser.getUsername());
                    Log.d("Name", objuser.getUsername());
                    newuser.setStatus(objuser.getStatus());
                    Log.d("Status", objuser.getStatus());
                    users.add(newuser);
                } */

                ArrayList<String> names = new ArrayList<String>();
                names.add("Kousalya");
                names.add("Sahana");
                names.add("Sudha");


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ContactsActivity.this, android.R.layout.simple_list_item_1, names);
                setListAdapter(adapter);

            //}
       // });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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
}
