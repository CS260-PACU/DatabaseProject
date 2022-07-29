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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private NoteDatabase db;
    private NoteDao noteDao;
    private EditText tvEntry;
    private TextView tvDisplay;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEntry = findViewById(R.id.tvEntry);
        tvDisplay = findViewById(R.id.tvDisplay);
        executor = Executors.newSingleThreadExecutor();
    }

    public void onClickClear(View view)
    {
        executor.execute(
                () -> {
                    noteDao.deleteAll();
                    view.post(() ->tvDisplay.setText(""));
                });
    }
    public void onClickCreate(View view)
    {
        executor.execute( () -> {
                    db = Room.databaseBuilder(getApplicationContext(),
                            NoteDatabase.class, "note-db").build();
                    noteDao = db.noteDao();
        }
        );
    }

    public void onClickAdd(View view)
    {
        Note localNote = new Note();

        localNote.msg = tvEntry.getText().toString();
        tvDisplay.setText("");

        executor.execute(
        //AsyncTask.execute(
                () -> {
            noteDao.insertAll(localNote);
        List<Note> notes = noteDao.getAll();

        for (Note aNote : notes)
        {
            view.post(() ->tvDisplay.append(aNote.msg + "\n"));
        }
                });

    }
}