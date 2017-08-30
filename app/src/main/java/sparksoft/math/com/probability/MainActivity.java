package sparksoft.math.com.probability;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;
import java.util.Stack;
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
                //trials.setEnabled(false);

                if(trial < 1 || trial > 52)
                {
                    Toast.makeText(getApplicationContext(), "Please input a valid number from 1 to 52", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cards);

                Stack<Integer> cards = new Stack<Integer>();
                for(int i = 0; i < 52; i++)
                {
                    cards.add(i);
                }



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

                int cardValue;




                for(int i = 0; i < trial; i++)
                {
                    Random die = new Random(System.currentTimeMillis());
                    int dieResult = Math.abs(die.nextInt())%6;
                    Log.d("RESULT", String.valueOf(dieResult));

                    Random wheel = new Random(System.currentTimeMillis());
                    int wheelResult = Math.abs(wheel.nextInt())%12;

                    Random deck = new Random(System.currentTimeMillis());
                    Integer deckResult = Math.abs(deck.nextInt()%cards.size());
                    //Log.i("Card", deckResult.toString());

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

                    Bitmap mBitmapDice = BitmapFactory.decodeResource(getResources(), R.drawable.dice);
                    Log.d("Width", Integer.toString(mBitmapDice.getWidth()));
                    Log.d("Width", Integer.toString(mBitmapDice.getHeight()));
                    Bitmap diceBitmap;
                    switch (dieResult)
                    {
                        case 1:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, 0, 0, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);

                            break;
                        case 2:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, mBitmapDice.getWidth()/3, 0, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);
                            break;
                        case 3:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, mBitmapDice.getWidth()/3*2, 0, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);
                            break;
                        case 4:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, 0, mBitmapDice.getHeight()/2, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);
                            break;
                        case 5:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, mBitmapDice.getHeight()/2, mBitmapDice.getWidth()/3, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);
                            break;
                        case 0:
                            diceBitmap = Bitmap.createBitmap(mBitmapDice, mBitmapDice.getWidth()/3*2, mBitmapDice.getHeight()/2, mBitmapDice.getWidth()/3, mBitmapDice.getHeight()/2);
                            ivDie.setImageBitmap(diceBitmap);
                            break;


                    }

                    ImageView ivCard = new ImageView(getApplicationContext());
                    ivCard.setLayoutParams(layoutParams);
                    int cardVal = cards.remove(deckResult.intValue());
                    Log.i("Card", Integer.toString(cardVal));

                    //Log.i("Bitmap", "width:" + mBitmap.getWidth());
                    //Log.i("Bitmap", "height:" + mBitmap.getHeight());

                    if(cardVal < 13)
                    {
                        Bitmap cardBitmap = Bitmap.createBitmap(mBitmap, mBitmap.getWidth()/13*cardVal, 0, mBitmap.getWidth()/13, mBitmap.getHeight()/5);
                        ivCard.setImageBitmap(cardBitmap);

                    }

                    else if (cardVal >= 13 && cardVal < 26)
                    {
                        Bitmap cardBitmap = Bitmap.createBitmap(mBitmap, mBitmap.getWidth()/13*(cardVal-13),mBitmap.getHeight()/5 , mBitmap.getWidth()/13, mBitmap.getHeight()/5);
                        ivCard.setImageBitmap(cardBitmap);
                    }
                    else if (cardVal >= 26 && cardVal < 39)
                    {
                        Bitmap cardBitmap = Bitmap.createBitmap(mBitmap, mBitmap.getWidth()/13*(cardVal-26),mBitmap.getHeight()/5 *2 , mBitmap.getWidth()/13, mBitmap.getHeight()/5);
                        ivCard.setImageBitmap(cardBitmap);
                    }
                    else if (cardVal >= 39 && cardVal < 52)
                    {
                        Bitmap cardBitmap = Bitmap.createBitmap(mBitmap, mBitmap.getWidth()/13*(cardVal-39),mBitmap.getHeight()/5 *3 , mBitmap.getWidth()/13, mBitmap.getHeight()/5);
                        ivCard.setImageBitmap(cardBitmap);
                    }




                    //Log.i("Card", cards.remove(deckResult.intValue()).toString());


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
                    results.addView(ivCard);
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
