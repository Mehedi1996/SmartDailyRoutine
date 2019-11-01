package com.example.smartdailyroute.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartdailyroute.R;
import com.example.smartdailyroute.databinding.ActivityAddNoteBinding;
import com.example.smartdailyroute.model.Note;
import com.example.smartdailyroute.viewmodel.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

         noteViewModel = ViewModelProviders.of(AddNoteActivity.this).get(NoteViewModel.class);
        binding.saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNote();
            }
        });
    }
    public void saveNote(){
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();
        int priority = Integer.valueOf(binding.editTextPriority.getText().toString());

        noteViewModel.insert(new Note(title, description, priority));
        Toast.makeText(this, " Add new note", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
