package com.example.lenovo.snotepad;

import android.content.Context;

import java.io.Serializable;  //Serializable class
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by LENOVO on 8/21/2017.
 */

public class Note implements Serializable {

  private long Ntime;
  private String Ntitle,Ncontent;

//Note constructor

    public Note(long dtime, String title, String content){

            Ntime = dtime;
            Ntitle = title;
            Ncontent = content;

    }

    public long getNtime() {
        return Ntime;
    }

    public String getNtitle() {
        return Ntitle;
    }

    public String getNcontent() {
        return Ncontent;
    }


    public void setNtime(long ntime) {
        Ntime = ntime;
    }

    public void setNtitle(String ntitle) {
        Ntitle = ntitle;
    }

    public void setNcontent(String ncontent) {
        Ncontent = ncontent;
    }


  public String getNdatetimeFormat(Context c){

      //SimpleDateFormat class is imported, used to format date and time //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
      SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", c.getResources().getConfiguration().locale);
        df.setTimeZone(TimeZone.getDefault());
        return df.format(new Date(Ntime));



  }







}
