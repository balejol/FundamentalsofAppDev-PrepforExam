package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MultimediaActivity extends AppCompatActivity {

    public static String sRecordedFileName;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private Button btnRec;
    private Button btnPlay;
    private Button btnStop;

    private boolean isRecording = false;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        btnRec = (Button) findViewById(R.id.button_rec);
        btnPlay = (Button) findViewById(R.id.button_play);
        btnStop = (Button) findViewById(R.id.button_stop);

        btnRec.setEnabled(true);
        btnPlay.setEnabled(true);
        btnStop.setEnabled(true);

        btnRec.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rec, 0, 0, 0);
        btnPlay.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play, 0, 0, 0);
        //btnStop.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stop, 0, 0, 0);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO,},0);
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},0);
        }

        sRecordedFileName = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/audio_file.3gpp";

        btnRec.setOnClickListener(recordClick);
        btnPlay.setOnClickListener(playClick);
        btnStop.setOnClickListener(stopClick);
    }

    View.OnClickListener recordClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            releaseMediaRecorder();
            try {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(sRecordedFileName);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.setAudioEncodingBitRate(16);
                mediaRecorder.setAudioSamplingRate(44100);
                mediaRecorder.prepare();
                mediaRecorder.start();

                isRecording = true;
                btnRec.setEnabled(false); btnPlay.setEnabled(true); btnStop.setEnabled(true);
            }
            catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                Toast.makeText(MultimediaActivity.this,"Sorry! "+e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    View.OnClickListener playClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            releaseMediaPlayer();
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(sRecordedFileName);
                mediaPlayer.prepare();
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btnRec.setEnabled(true); btnPlay.setEnabled(true); btnStop.setEnabled(false);
                    }
                });
                isPlaying = true;
                btnRec.setEnabled(true); btnPlay.setEnabled(false); btnStop.setEnabled(true);
            }
            catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                Toast.makeText(MultimediaActivity.this,"Sorry! "+e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    private void releaseMediaRecorder() {
        if (isRecording == true) {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
        }
    }

    private void releaseMediaPlayer() {
        if (isPlaying == true) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    View.OnClickListener stopClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                releaseMediaRecorder();
                releaseMediaPlayer();
                btnRec.setEnabled(true); btnPlay.setEnabled(true); btnStop.setEnabled(false);
            }
            catch (IllegalStateException e) {
                Log.e("StopClick", "Error while stopping the recording.", e);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaRecorder();
        releaseMediaPlayer();
    }
}