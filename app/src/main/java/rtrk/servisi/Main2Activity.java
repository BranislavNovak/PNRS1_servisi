package rtrk.servisi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button bOne, bStart, bStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bOne = findViewById(R.id.button1);
        bStart = findViewById(R.id.buttonStart2);
        bStop = findViewById(R.id.buttonStop2);

        bOne.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.buttonStart2:
                break;
            case R.id.buttonStop2:
                break;
        }
    }
}
