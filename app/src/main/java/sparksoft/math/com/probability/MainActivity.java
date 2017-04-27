package sparksoft.math.com.probability;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity{

    int count = 0;
    Timer myTimer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView mVideoView  = (VideoView)findViewById(R.id.vidCard);
        //mVideoView.setMediaController(new MediaController(this));
        String path = "android.resource://" + getPackageName() + "/" + R.raw.card1;
        mVideoView.requestFocus();
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.seekTo(2);




        Button btnRun = (Button)findViewById(R.id.run);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText trials = (EditText)findViewById(R.id.etTrials);
                int trial = Integer.parseInt(trials.getText().toString());
                trials.setEnabled(false);

                LinearLayout llResults = (LinearLayout)findViewById(R.id.llResults);
                llResults.removeAllViews();

                ScrollView svResults = (ScrollView)findViewById(R.id.svResults);





                VideoView mVideoView  = (VideoView)findViewById(R.id.vidCard);
                //mVideoView.setMediaController(new MediaController(this));
                String path = "android.resource://" + getPackageName() + "/" + R.raw.card1;
                mVideoView.requestFocus();
                mVideoView.setVideoURI(Uri.parse(path));
                mVideoView.seekTo(100);

                mVideoView.start();

                ImageView image= (ImageView) findViewById(R.id.wheel);
                RotateAnimation rotate = new RotateAnimation(0, 360*10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                rotate.setInterpolator(new LinearInterpolator());



                image.startAnimation(rotate);

                count = 0;
                myTimer = new Timer();
                myTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TimerMethod();
                    }

                }, 0, 200);

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;


                for(int i = 0; i < trial; i++)
                {
                    Random die = new Random(System.currentTimeMillis());
                    int dieResult = Math.abs(die.nextInt())%6;
                    Log.d("RESULT", String.valueOf(dieResult));

                    Random wheel = new Random(System.currentTimeMillis());
                    int wheelResult = Math.abs(wheel.nextInt())%12;

                    Random deck = new Random(System.currentTimeMillis());
                    int deckResult = deck.nextInt()%52;

                    TextView tvTrial = new TextView(getApplicationContext());
                    android.view.ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(svResults.getWidth(),svResults.getHeight()/5);
                    tvTrial.setBackgroundColor(Color.GRAY);
                    tvTrial.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                    tvTrial.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tvTrial.setText("TRIAL " + (i + 1));


                    ImageView ivWheel = new ImageView(getApplicationContext());
                    layoutParams = new ViewGroup.LayoutParams(width/3,height/15*4);
                    //ivWheel.setPivotX(0.5f);
                    //ivWheel.setPivotY(0.5f);
                    float angle = 360/12*wheelResult;
                    ivWheel.setRotation(angle);
                    ivWheel.setLayoutParams(layoutParams);


                    ivWheel.setImageDrawable(getDrawable(R.drawable.w));
/*
                    Matrix matrix = new Matrix();
                    ivWheel.setScaleType(ImageView.ScaleType.MATRIX);   //required

                    matrix.postRotate(180f, ivWheel.getDrawable().getBounds().width()/2, ivWheel.getDrawable().getBounds().height()/2);
                    ivWheel.setImageMatrix(matrix);
*/

                    ImageView ivDie = new ImageView(getApplicationContext());
                    ivDie.setLayoutParams(layoutParams);



                    switch (dieResult)
                    {
                        case 1:

                            ivDie.setImageDrawable(getDrawable(R.drawable.d1));
                            break;
                        case 2:
                            ivDie.setImageDrawable(getDrawable(R.drawable.d2));
                            break;
                        case 3:
                            ivDie.setImageDrawable(getDrawable(R.drawable.d3));
                            break;
                        case 4:
                            ivDie.setImageDrawable(getDrawable(R.drawable.d4));
                            break;
                        case 5:
                            ivDie.setImageDrawable(getDrawable(R.drawable.d5));
                            break;
                        case 0:
                            ivDie.setImageDrawable(getDrawable(R.drawable.d6));
                            break;


                    }

                    LinearLayout resultsContainer = new LinearLayout(getApplicationContext());
                    android.view.ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width,height/3);
                    resultsContainer.setOrientation(LinearLayout.VERTICAL);
                    resultsContainer.setLayoutParams(lp);

                    LinearLayout results = new LinearLayout(getApplicationContext());
                    lp = new ViewGroup.LayoutParams(width,height/15*4);
                    results.setOrientation(LinearLayout.HORIZONTAL);

                    results.setLayoutParams(lp);


                    results.addView(ivWheel);
                    results.addView(ivDie);
                    resultsContainer.addView(tvTrial);
                    resultsContainer.addView(results);
                    llResults.addView(resultsContainer, i);


                }
                trials.setEnabled(true);

            }
        });
        /*
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        intent.setDataAndType(Uri.parse(path), "video/*");
        startActivity(intent);

        Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW);
        File file = new File(path);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        myIntent.setDataAndType(Uri.fromFile(file),mimetype);
        startActivity(myIntent);
        */
    }

    private void TimerMethod()
    {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            ImageView die = (ImageView)findViewById(R.id.die);
            switch (count)
            {
                case 0: die.setImageDrawable(getDrawable(R.drawable.a1));
                    break;
                case 1: die.setImageDrawable(getDrawable(R.drawable.a1));
                    break;
                case 2: die.setImageDrawable(getDrawable(R.drawable.a2));
                    break;
                case 3: die.setImageDrawable(getDrawable(R.drawable.a3));
                    break;
                case 4: die.setImageDrawable(getDrawable(R.drawable.a4));
                    break;
                case 5: die.setImageDrawable(getDrawable(R.drawable.a5));
                    break;
                case 6: die.setImageDrawable(getDrawable(R.drawable.a6));
                    break;
                case 7: die.setImageDrawable(getDrawable(R.drawable.a7));
                    break;
                case 8: die.setImageDrawable(getDrawable(R.drawable.a8));
                    break;
                case 9: die.setImageDrawable(getDrawable(R.drawable.a9));
                    break;
                case 10: die.setImageDrawable(getDrawable(R.drawable.a10));
                break;
                case 11: die.setImageDrawable(getDrawable(R.drawable.a12));
                    break;
                case 12: die.setImageDrawable(getDrawable(R.drawable.a12));
                    break;
                case 13: die.setImageDrawable(getDrawable(R.drawable.a13));
                    break;

            }


            count++;
            if(count > 13) myTimer.cancel();

        }
    };

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
}
