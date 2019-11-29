package com.example.smartdailyroute.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdailyroute.R;
import com.example.smartdailyroute.model.Note;
import com.example.smartdailyroute.view.EditNoteActivity;
import com.example.smartdailyroute.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note>allNotes = new ArrayList<>();
    private OnitemClickListener onitemClickListener;
    private OnDeleteClickListener onDeleteClickListener;
    private OnEditClickListener onEditClickListener;

    public NoteAdapter(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }



    @NonNull
    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position) {

        Note currentNote = allNotes.get(position);

        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.setClickListener();
        holder.setEditClickListener();

    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void setNote(List<Note>notes){
        this.allNotes = notes;
        notifyDataSetChanged();
    }
    public Note getNoteAt(int position){
        return allNotes.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private ImageView edit, delete;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            edit = itemView.findViewById(R.id.ivRowEdit);
            delete = itemView.findViewById(R.id.ivRowDelete);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (onitemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onitemClickListener.onItemClick(allNotes.get(position));

                    }
                }
            });
        }

        public void setClickListener() {

            delete.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();

                @Override
                public void onClick(View view) {
                    if (onDeleteClickListener != null && position != RecyclerView.NO_POSITION) {
                        onDeleteClickListener.onDeleteItem(allNotes.get(position));
                    }

                }
            });
        }

        public void setEditClickListener(){
            edit.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();

                @Override
                public void onClick(View view) {
                    if (onEditClickListener!= null && position != RecyclerView.NO_POSITION) {
                        onEditClickListener.onEditItem(allNotes.get(position));


                    }
                }
            });
        }

    }

    //Delete left to right
    public interface OnitemClickListener{
        void onItemClick(Note note);
    }
    public void setOnitemClick(OnitemClickListener onitemClick){
        this.onitemClickListener = onitemClick;

    }

    //delete single Item

    public interface OnDeleteClickListener{
        void onDeleteItem(Note note);
    }


    public interface OnEditClickListener{

        void onEditItem(Note note);
    }
    public void setOnEdititemClick(OnEditClickListener onEdititemClick){
        this.onEditClickListener = onEdititemClick;
    }

}
