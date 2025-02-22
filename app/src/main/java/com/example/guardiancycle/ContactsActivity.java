package com.example.guardiancycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ListView contactListView;
    private ArrayList<String> contacts;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactListView = findViewById(R.id.contactListView);
        contacts = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        contactListView.setAdapter(adapter);

        Button addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String newContact = data.getStringExtra("contact");
            contacts.add(newContact);
            adapter.notifyDataSetChanged();
        }
    }
}
