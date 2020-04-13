package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
    private class ButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String value = ((Button)view).getText().toString();
            String oldText = text.getText().toString();

            String newText = oldText + ", " + value;
            text.setText(newText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

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
