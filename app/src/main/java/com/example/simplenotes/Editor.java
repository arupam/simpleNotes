package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Editor extends AppCompatActivity {
    EditText editTextContent , editTextTitle;
    Context context;
    notesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editTextContent=findViewById(R.id.editorContent);
        editTextTitle=findViewById(R.id.editorTitle);

        Intent intent = getIntent();
        final int noteId= intent.getIntExtra("noteId",-1);
        if(noteId!=-1){
            editTextTitle.setText(MainActivity.mTitle.get(noteId));
            editTextContent.setText(MainActivity.mContent.get(noteId));

        }



        //Save button
        FloatingActionButton save = findViewById(R.id.editorSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteId==-1){
                    addNote();

                }
                else
                {//editing note
                    updateNote(noteId);


                }
               // Intent ii= new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(ii);
                //Toast.makeText(Editor.this, "Note Saved!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void updateNote(int noteId) {
        MainActivity.mContent.set(noteId,editTextContent.getText().toString());
        MainActivity.mTitle.set(noteId,editTextTitle.getText().toString());
        //adapter.notifyItemChanged(noteId); This is crashing the app
        Toast.makeText(Editor.this, "Note Updated!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));


    }

    private void addNote() {
        MainActivity.mContent.add(editTextContent.getText().toString());
        MainActivity.mTitle.add(editTextTitle.getText().toString());
        Toast.makeText(Editor.this, "Note saved!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}

