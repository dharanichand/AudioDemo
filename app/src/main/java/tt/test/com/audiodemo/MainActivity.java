package tt.test.com.audiodemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void play(View view)
    {
        //mediaPlayer = MediaPlayer.create(this,R.raw.lali);
        mediaPlayer.start();
    }
    public void pause(View view)
    {
       // MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.lali);
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.lali);
       // mediaPlayer= MediaPlayer.create(this, Uri.parse("https://gaana.com/song/sawan-aaya-hai"));
         audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
         int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);/*max volume of device*/
         int currVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);/*current volume of  device*/

         SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
          volumeControl.setMax(maxVolume);
          volumeControl.setProgress(currVolume);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                Log.i("Seek Bar Value", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

                            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
         final SeekBar position=(SeekBar) findViewById(R.id.position);
         position.setMax(mediaPlayer.getDuration());


         new Timer().scheduleAtFixedRate(new TimerTask()
         {
             public void run()
             {
                 position.setProgress(mediaPlayer.getCurrentPosition());
             }
         },0,10000);
         position.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                 Log.i("Audio Position", Integer.toString(progress));
                 mediaPlayer.seekTo(progress);



             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
    }
}
