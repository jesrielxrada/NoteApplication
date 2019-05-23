package com.jesriel_rada.java.simplenoteapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jesriel_rada.java.simplenoteapp.R;
import com.jesriel_rada.java.simplenoteapp.data.adapter.NoteAdapter;
import com.jesriel_rada.java.simplenoteapp.data.model.Note;
import com.jesriel_rada.java.simplenoteapp.view_model.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Observer<List<Note>> {

    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //init view model
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, this);

        //init adapter
        noteAdapter = new NoteAdapter();

        //init adapter to recyclerview
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onChanged(@Nullable List<Note> notes) {
        //update RecyclerView
        noteAdapter.setNotes(notes);
    }
}
