package com.example.smartdailyroute.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.smartdailyroute.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
   private LiveData<List<Note>>getNote;

   public NoteRepository(Application application){

       NoteDatabase database = NoteDatabase.getInstance(application);
       noteDao = database.noteDao();
       getNote = noteDao.getAllNotes();
   }
   public void insert(Note note){
       new InsertAsynctask(noteDao).execute(note);

    }
    public void update(Note note){

       new UpdateAsynctask(noteDao).execute(note);
    }
    public void delete(Note note){

       new DeleteAsynctask(noteDao).execute(note);
    }
    public void deleteAll(Note note){

    }
    public LiveData<List<Note>> getAllNotes(){
       return getNote;
    }

    private static class InsertAsynctask extends AsyncTask<Note, Void, Void>{

       private NoteDao noteDao;

       private InsertAsynctask(NoteDao noteDao){
           this.noteDao = noteDao;
       }
        @Override
        protected Void doInBackground(Note... notes) {

           noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAsynctask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAsynctask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }


}
