package com.example.guardiancycle;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FakeCallActivity extends AppCompatActivity {

    private TextView callStatusTextView;
    private Button endCallButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_call);

        callStatusTextView = findViewById(R.id.callStatusTextView);
        endCallButton = findViewById(R.id.endCallButton);

        // Start the fake call
        startFakeCall();

        // Set up end call button
        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endFakeCall();
            }
        });
    }

    private void startFakeCall() {
        // Simulate a fake call
        callStatusTextView.setText("Incoming Call..."); // Update UI to show incoming call
        // You can add sound or vibration here to simulate a real call
    }

    private void endFakeCall() {
        // End the fake call
        callStatusTextView.setText("Call Ended");
        // You can add logic to return to MainActivity or perform other actions
        finish(); // Close the FakeCallActivity
    }
}
