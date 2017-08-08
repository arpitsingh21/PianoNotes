package com.example.dell.xeirius;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.example.dell.xeirius.R.raw.a1;

public class MainActivity extends AppCompatActivity {
    static Button play, stop;
    static EditText notes;
boolean status=true;
         MediaPlayer mediaPlayer;
         String no [];
         int currentTrack = 0;
        int res[];
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            play=(Button)findViewById(R.id.button);
            stop=(Button)findViewById(R.id.button2);
            stop.setEnabled(false);
            notes=(EditText)findViewById(R.id.editText);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
               if(mediaPlayer!=null)
               {

                   mediaPlayer=null;

               }
               play.setEnabled(false);

                    String s = notes.getText().toString();
                   String[] s1 = s.split(" "); // splitting the notes using " "
                    status=true;
                    res=new int[s1.length];

                    no=s1; //copying length of the notes array
              try {
                  for (int i = 0; i < s1.length; i++) {
                      switch (s1[i]) {                          // fill the array with notes in textview
                          case "A1":
                              res[i]=R.raw.a1;
                              break;
                          case "A1s":
                              res[i]=R.raw.a1s;
                              break;
                          case "B1":
                              res[i]=R.raw.b1;
                              break;
                          case "C1":
                              res[i]=R.raw.c1;
                              break;
                          case "C1s":
                              res[i]=R.raw.c1s;
                              break;
                          case "D1":
                              res[i]=R.raw.d1;
                              break;
                          case "D1s":
                              res[i]=R.raw.d1s;
                              break;
                          case "E1":
                              res[i]=R.raw.e1;
                              break;
                          case "F1":
                              res[i]=R.raw.f1;
                              break;
                          case "F1s":
                              res[i]=R.raw.f1s;
                              break;
                          case "G1":
                              res[i]=R.raw.g1;
                              break;
                          case "G1s":
                              res[i]=R.raw.g1s;
                              break;
                          case ".":Thread.sleep(50);
                             break;

                      }
                  }
          if (res[currentTrack] == 0) {
                  ++currentTrack;
              }
                  mediaPlayer = MediaPlayer.create(MainActivity.this, res[currentTrack]); //Playing the first note in the array
                  mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                          @Override
                                  public void onCompletion(MediaPlayer arg0) {
                   try{

                              arg0.release();
                              if (currentTrack < no.length) {
                                  currentTrack++;
                                  if (res[currentTrack] == 0 && status) {

                                      while (res[currentTrack] == 0) {

                                          currentTrack++;
                                      }
                                      try {
                                          Thread.sleep(50);
                                      } catch (InterruptedException e) {
                                          e.printStackTrace();
                                      }

                                      arg0 = MediaPlayer.create(MainActivity.this, res[currentTrack]);
                                      arg0.setOnCompletionListener(this);//Playing the following note in array
                                      arg0.start();


                                  } else {
                                      if (status) {
                                          arg0 = MediaPlayer.create(MainActivity.this, res[currentTrack]);
                                          arg0.setOnCompletionListener(this);
                                          arg0.start();
                                      }
                                  }
                              }
                              if (currentTrack == res.length) {

                                  currentTrack = 0;
                              }

                          }
                          catch(ArrayIndexOutOfBoundsException e){e.printStackTrace();
                          currentTrack=0;
                          }

                          }


               });

                       stop.setEnabled(true);
                       mediaPlayer.start();



              }
              catch(Exception e){e.printStackTrace();}


                }
        });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        status=false;           //updating the current track to initial
                         currentTrack=-1;
                        stop.setEnabled(false);   //Setting stop button false and play button true
                        play.setEnabled(true);
                    }
                    catch(IllegalStateException e){e.printStackTrace();}
                }
            });


    }
}

