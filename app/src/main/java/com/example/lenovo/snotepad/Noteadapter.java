package com.example.lenovo.snotepad;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by LENOVO on 8/25/2017.
 */
//extends Arrayadapter
public class Noteadapter extends ArrayAdapter<Note> {

    public Noteadapter(Context context, int resource, ArrayList<Note> notes) {
        super(context, resource, notes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent ){



        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_snotes,null);

        }


        Note note = getItem(position);


        if(note != null){

            TextView title =(TextView) convertView.findViewById(R.id.list_note_title);
            TextView date =(TextView) convertView.findViewById(R.id.list_note_date);
            TextView content =(TextView) convertView.findViewById(R.id.list_note_content);


            title.setText(note.getNtitle());   //getNtitle method from Note class we created earlier
            date.setText(note.getNdatetimeFormat(getContext()));

            //to edit the note we use this

            if (note.getNcontent().length() > 50){

                content.setText(note.getNcontent().substring(0, 50));

            }  else{

                content.setText(note.getNcontent());

            }




        }

      return convertView;










    }



}
