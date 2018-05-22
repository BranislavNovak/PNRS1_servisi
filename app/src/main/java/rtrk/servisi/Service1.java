package rtrk.servisi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by student on 22.5.2018.
 */

public class Service1 extends Service {
    private static final String LOG_TAG = "ExampleService";
    private static final long PERIOD = 1000L;
    public static final String PREFERENCES_NAME = "PreferenceFile";
    private Context context = this;

    private RunnableExample mRunnable;

    @Override
    public void onCreate() {
        super.onCreate();
        mRunnable = new RunnableExample();
        mRunnable.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRunnable.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    private class RunnableExample implements Runnable {
        private Handler mHandler;
        private boolean mRun = false;

        public RunnableExample() {
            mHandler = new Handler(getMainLooper());
        }

        public void start() {
            mRun = true;
            mHandler.postDelayed(this, PERIOD);
        }

        public void stop() {
            mRun = false;
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (!mRun) {
                return;
            }

            String s = generateString();
            SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE).edit();
            editor.putString("text_to_print", s);
            editor.apply();

            Log.d(LOG_TAG, "Hello from Runnable");
            mHandler.postDelayed(this, PERIOD);
        }
    }

    public String generateString() {
            String uuid = UUID.randomUUID().toString();
            return "uuid = " + uuid;
        }

}
