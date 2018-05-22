package rtrk.servisi;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button bTwo, bStart, bStop;
    private TextView tvText;
    public static final String PREFERENCES_NAME = "PreferenceFile";
    private static final long PERIOD = 500L;
    //private ThreadExample mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bTwo = findViewById(R.id.button2);
        bStart = findViewById(R.id.buttonStart1);
        bStop = findViewById(R.id.buttonStop1);
        tvText = findViewById(R.id.text_to_change);

        bTwo.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*while(true)*/ {
                    SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
                    String text_to_print = sharedPref.getString("text_to_print", null);
                    if (text_to_print != null) {
                        Log.d(TAG, "run: enter");
                        tvText.setText(text_to_print.toString());
                    }
                    try {
                        Thread.sleep(PERIOD); //milliseconds
                    } catch (InterruptedException e) {
                        // interrupted finish thread

                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button2:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                break;

            case R.id.buttonStart1:
                Intent intent1 = new Intent(this, Service1.class);
                startService(intent1);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                /*while(true)*/ {
                            SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
                            String text_to_print = sharedPref.getString("text_to_print", null);
                            if (text_to_print != null) {
                                Log.d(TAG, "run: enter");
                                tvText.setText(text_to_print.toString());
                            }
                            try {
                                Thread.sleep(PERIOD); //milliseconds
                            } catch (InterruptedException e) {
                                // interrupted finish thread

                            }
                        }
                    }
                });
                break;

            case R.id.buttonStop1:
                Intent intent2 = new Intent(this, Service1.class);
                stopService(intent2);
                break;
        }
    }
}
