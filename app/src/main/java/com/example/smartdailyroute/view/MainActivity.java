package com.example.smartdailyroute.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartdailyroute.R;
import com.example.smartdailyroute.adapter.NoteAdapter;
import com.example.smartdailyroute.databinding.ActivityMainBinding;
import com.example.smartdailyroute.model.Note;
import com.example.smartdailyroute.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnDeleteClickListener {

    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter( this);
        binding.recyclerView.setAdapter(adapter);


        noteViewModel = ViewModelProviders.of(MainActivity.this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Toast.makeText(MainActivity.this, "Onchange", Toast.LENGTH_SHORT).show();
                adapter.setNote(notes);
            }
        });

        binding.AddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });




        adapter.setOnitemClick(new NoteAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(Note note) {
             /*   Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);*/
            }
        });

        adapter.setOnEdititemClick(new NoteAdapter.OnEditClickListener() {
            @Override
            public void onEditItem(Note note) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });

        //This method is single Note Delete

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerView);
    }


    @Override
    public void onDeleteItem(Note note) {

      noteViewModel.delete(note);

        Toast.makeText(this, "Single Note is Deleted", Toast.LENGTH_SHORT).show();
    }


}
