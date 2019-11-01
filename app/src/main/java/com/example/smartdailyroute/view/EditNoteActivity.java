package com.example.smartdailyroute.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartdailyroute.R;
import com.example.smartdailyroute.databinding.ActivityEditNoteBinding;
import com.example.smartdailyroute.model.Note;
import com.example.smartdailyroute.viewmodel.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    ActivityEditNoteBinding binding;
    private NoteViewModel noteViewModel;
    private Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note);
        noteViewModel = ViewModelProviders.of(EditNoteActivity.this).get(NoteViewModel.class);

        binding.saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            updateNote();
            }
        });
    }
    public void updateNote(){

        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();
        int priority = Integer.valueOf(binding.editTextPriority.getText().toString());


        noteViewModel.update(new Note(title, description, priority));

        Toast.makeText(EditNoteActivity.this, " update new note", Toast.LENGTH_SHORT).show();

        binding.editTextTitle.setText("");
        binding.editTextDescription.setText("");
        binding.editTextPriority.setText("");

        Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
