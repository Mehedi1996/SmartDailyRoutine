package com.example.smartdailyroute.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartdailyroute.data.NoteDao;
import com.example.smartdailyroute.data.NoteDatabase;
import com.example.smartdailyroute.model.Note;

public class EditNoteViewModel extends AndroidViewModel {

    private NoteDao noteDao;
    private NoteDatabase database;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
    }
/*
    public LiveData<Note> getNote(String noteId) {
        return noteDao.getNote(noteId);
    }*/
}
