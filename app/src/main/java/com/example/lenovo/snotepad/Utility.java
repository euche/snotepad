package com.example.lenovo.snotepad;

/**
 * Created by LENOVO on 8/23/2017.
 */

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.util.Log;




public class Utility {


    // Filename extension is necessary
public static final String FILE_EXTENSION = ".bin";
 public static String TAG = Utility.class.getName();





public static boolean saveNote(Context c , Note n){

      String filename =  String.valueOf(n.getNtime()) + FILE_EXTENSION;        //get date time + extension as file name
      FileOutputStream fos;
      ObjectOutputStream ois;

try{

     fos = c.openFileOutput(filename,c.MODE_PRIVATE);          //to open  file
     ois = new ObjectOutputStream(fos);
     ois.writeObject(n);
     ois.close();                                          // close ObjectOutputStream before fileOutputStrream
     fos.close();

}

catch(Exception e){

    Log.e(TAG, "An error just occured, Kindly fix!!!!!!");

}


return true;
}


  // Method to load previous saved notes.

public static ArrayList<Note> getAllsavednotes(Context c){

    //new arraylist

    ArrayList<Note> notes  = new ArrayList<>();

    File filesDirectory =  c.getFilesDir();        //new File object
    ArrayList<String>  notefiles = new ArrayList<>();

   for(String f : filesDirectory.list()){

       if(f.endsWith(FILE_EXTENSION)){
           //filename ends with ".bin" add the name of file to the arraylist

          notefiles.add(f);

       }

   }
    FileInputStream fis;
    ObjectInputStream ois;


    for(int i =0; i < notefiles.size(); i++){

        try{

            fis = c.openFileInput(notefiles.get(i));
            ois = new ObjectInputStream(fis);

            notes.add((Note)ois.readObject()); /// //arraylist of Object

            fis.close();
            ois.close();


        }

        catch (Exception e){

            Log.e(TAG, e.getMessage());



            return null;
        }


    }

 return notes;
}


public static Note getNoteByName(Context context, String fN){

    File file = new File(context.getFilesDir(),fN);
    Note n;


    //Safety Check
    if(file.exists()){

        FileInputStream fis;
        ObjectInputStream ois;

        try{

           fis = context.openFileInput(fN);
            ois = new ObjectInputStream(fis);

           n = (Note) ois.readObject();

              fis.close();
              ois.close();

        }
        catch( Exception e){

            Log.e(TAG, e.getMessage());
            return null;

        }

        return n;
    }


return null;

}



    static void deletenote(Context c, String filename){

        File dir = c.getFilesDir();
        File file = new File(dir,filename);


        if (file.exists()){

            file.delete();

        }





    }






}
