package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Editor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Save button
        FloatingActionButton save = findViewById(R.id.editorSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ii);
                Toast.makeText(Editor.this, "Note Saved!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
