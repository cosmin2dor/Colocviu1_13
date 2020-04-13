package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    private Button registerButton;
    private Button cancelButton;
    private TextView text;

    private RegisterButtonOnClickListener registerButtonOnClickListener = new RegisterButtonOnClickListener();
    private class RegisterButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        }
    }

    private CancelButtonOnClickListener cancelButtonOnClickListener = new CancelButtonOnClickListener();
    private class CancelButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(Activity.RESULT_CANCELED, new Intent());
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        registerButton = (Button)findViewById(R.id.registerButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        text = (TextView)findViewById(R.id.text);

        registerButton.setOnClickListener(registerButtonOnClickListener);
        cancelButton.setOnClickListener(cancelButtonOnClickListener);

        Intent intent = getIntent();
        String value = intent.getStringExtra(Constants.TEXT_KEY);
        text.setText(value);
    }
}
