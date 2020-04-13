package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button navigateButton;
    private TextView text;

    private int counter;
    private boolean serviceStarted = false;

    private IntentFilter intentFilter = new IntentFilter();

    private NavigateButtonOnClickListener navigateButtonOnClickListener = new NavigateButtonOnClickListener();
    private class NavigateButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String textValue = text.getText().toString();

            Intent intent = new Intent("ro.pub.cs.systems.eim.Colocviu1_13.Colocviu1_13SecondaryActivity");
            intent.putExtra(Constants.TEXT_KEY, textValue);
            startActivityForResult(intent, Constants.SECONDARY_REQUEST_CODE);

            text.setText("");
            counter = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK: {
                Toast.makeText(this, Constants.SUCCESS_MESSAGE, Toast.LENGTH_LONG).show();
                break;
            }
            case RESULT_CANCELED: {
                Toast.makeText(this, Constants.CANCEL_MESSAGE, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    private ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
    private class ButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String value = ((Button)view).getText().toString();
            String oldText = text.getText().toString();

            String newText = oldText + ", " + value;
            text.setText(newText);

            counter++;
            Log.d("MyTag", "Button pressed: " + counter + " times");

            if (counter >= 4 && !serviceStarted) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                intent.putExtra("instruction", newText);
                getApplicationContext().startService(intent);
                serviceStarted = true;
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MyTag", intent.getStringExtra("Message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter", counter);
        Log.d("MyTag", "Saved counter");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        counter = savedInstanceState.getInt("counter");
        Log.d("MyTag", "Restored Counter");
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        intentFilter.addAction("Broadcast");

        // First run
        if (savedInstanceState == null) {
            counter = 0;
        }

        northButton = (Button)findViewById(R.id.northButton);
        southButton = (Button)findViewById(R.id.southButton);
        eastButton = (Button)findViewById(R.id.eastButton);
        westButton = (Button)findViewById(R.id.westButton);
        navigateButton = (Button)findViewById(R.id.navigateButton);
        text = (TextView)findViewById(R.id.text);

        northButton.setOnClickListener(buttonOnClickListener);
        southButton.setOnClickListener(buttonOnClickListener);
        eastButton.setOnClickListener(buttonOnClickListener);
        westButton.setOnClickListener(buttonOnClickListener);
        navigateButton.setOnClickListener(navigateButtonOnClickListener);
    }
}
