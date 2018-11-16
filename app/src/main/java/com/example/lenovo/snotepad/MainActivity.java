package com.example.lenovo.snotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView Nlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nlv = (ListView) findViewById(R.id.lv_notes); //listview







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                Intent na = new Intent(MainActivity.this, noteactive.class);      //We are using Mainactivity.class instead of using "this"  because we are using it inside a method not outside.
                startActivity(na);




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    @Override
    protected void onResume(){

        super.onResume();

      Nlv.setAdapter(null);

        ArrayList<Note> notes = Utility.getAllsavednotes(this);

        // to show if there are any saved notes;
        if(notes == null || notes.size() == 0){

            Toast.makeText(this,"No Saved notes found", Toast.LENGTH_SHORT).show();

            return;

        }  else {

                Noteadapter nl = new Noteadapter(this, R.layout.item_snotes, notes);

            Nlv.setAdapter(nl);

            // implementing the listview onClicklistener method

            Nlv.setOnItemClickListener( new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //get file name
                   String fileName = ((Note)Nlv.getItemAtPosition( position)).getNtime()+ Utility.FILE_EXTENSION;

                    //Intent to display stored Note to available class.

                    Intent viewNoteIntent = new Intent(getApplicationContext(),noteactive.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);


                }
            });

        }


    }


}
