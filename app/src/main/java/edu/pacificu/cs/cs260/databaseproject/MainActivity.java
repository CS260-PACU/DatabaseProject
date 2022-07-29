package edu.pacificu.cs.cs260.databaseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.channels.AsynchronousByteChannel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteDatabase db;
    private NoteDao noteDao;
    private EditText tvEntry;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEntry = findViewById(R.id.tvEntry);
        tvDisplay = findViewById(R.id.tvDisplay);
    }

    public void onClickCreate(View view)
    {
        db = Room.databaseBuilder(getApplicationContext(),
                NoteDatabase.class, "note-db").build();
        noteDao = db.noteDao();
    }

    public void onClickAdd(View view)
    {
        Note localNote = new Note();

        localNote.msg = tvEntry.getText().toString();

        tvDisplay.setText("");

        AsyncTask.execute( () -> {
            noteDao.insertAll(localNote);
        List<Note> notes = noteDao.getAll();

        for (Note aNote : notes)
        {
            tvDisplay.append(aNote.msg + "\n");
        } });

    }
}