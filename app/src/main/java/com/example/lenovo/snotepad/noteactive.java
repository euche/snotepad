package com.example.lenovo.snotepad;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class noteactive extends AppCompatActivity {


    private EditText sntitle;
    private EditText sncontent;


    private String mNoteFilename;
    private Note mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteactive);

        sntitle = (EditText) findViewById(R.id.sntitle);
        sncontent =(EditText) findViewById(R.id.sncontent);


// connecting Mainacativity and loadactive together for the loading of saved notes.
        mNoteFilename = getIntent().getStringExtra("NOTE_FILE");
        if(mNoteFilename != null && !mNoteFilename.isEmpty()){

               mLoadedNote = Utility.getNoteByName(getApplicationContext(),mNoteFilename);

            if(mLoadedNote!=null){

                sntitle.setText(mLoadedNote.getNtitle());
                sncontent.setText(mLoadedNote.getNcontent());


            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.save_note) {

//  Running Method SAVENOTE()

          savenote();


            return true;
        }

        if(id == R.id.action_delete){

// Running METHOD  DELETENOTE()
           deletenote();


        }


        return super.onOptionsItemSelected(item);


    }

private void savenote(){
       //we have to check if a new note is being saved or an
       //existing note is being edited.
    Note n;

    // We have to check if title and content are empty

    if(sntitle.getText().toString().trim().isEmpty()||sncontent.getText().toString().trim().isEmpty()){

        Toast.makeText(this,"Please enter a title and write something", Toast.LENGTH_SHORT).show();
        return;

    }



    if(mLoadedNote == null) {
        n = new Note(System.currentTimeMillis(), sntitle.getText().toString(), sncontent.getText().toString());
        //System.currenttimeMillis to get current time on device, sntitle/get text then convert to String.Same for content
    }
    else{
        n = new Note(mLoadedNote.getNtime(), sntitle.getText().toString(), sncontent.getText().toString());


    }

    if(Utility.saveNote(this,n)){

        Toast.makeText(this,"Your Note has been Saved.",Toast.LENGTH_LONG);

    }
    else {

        Toast.makeText(this,"Your Note was not saved Successfully. Please ensure you have enough space on your device.",Toast.LENGTH_SHORT).show();
    }

    finish();

}

// Method for deleting note

    private void deletenote(){

        if(mLoadedNote == null){
            finish();

        }else{

            // Using AlertDialog to notify USER of Imminent Delete action.
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("You are about delete. "+sntitle.getText().toString()+" Are you sure")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface d, int i) {
                                    Utility.deletenote(getApplicationContext(), mLoadedNote.getNtime()+Utility.FILE_EXTENSION);

                                    Toast.makeText(getApplicationContext(),sntitle.getText().toString()+" is deleted",Toast.LENGTH_SHORT).show();
                                   finish();
                                }
                            })

                    .setNegativeButton("No",null)
                    // We are about to make this dialog not cancellable(no exit).
                    .setCancelable(false);

             dialog.show();

        }





    }















}
