package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button navigateButton;
    private TextView text;

    private int counter;

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
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

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
    }
}
