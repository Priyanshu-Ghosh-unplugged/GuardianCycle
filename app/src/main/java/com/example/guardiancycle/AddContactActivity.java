package com.example.guardiancycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText contactNameEditText;
    private EditText contactNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactNameEditText = findViewById(R.id.contactNameEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);

        Button saveContactButton = findViewById(R.id.saveContactButton);
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = contactNameEditText.getText().toString() + ": " + contactNumberEditText.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("contact", contact);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
