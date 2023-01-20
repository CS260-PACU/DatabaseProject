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
    private byte mCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEntry = findViewById(R.id.tvEntry);
        tvDisplay = findViewById(R.id.tvDisplay);
        executor = Executors.newSingleThreadExecutor();
        executor.execute( () -> {
                    db = Room.databaseBuilder(getApplicationContext(),
                            NoteDatabase.class, "note-db").build();
                    noteDao = db.noteDao();
                }
        );
        mCounter = 0;
        showAllNotes ();
    }

    public void onClickClear(View view)
    {
        executor.execute(
                () -> {
                    noteDao.deleteAll();
                    view.post(() ->tvDisplay.setText(""));
                });


    }
    public void showAllNotes()
    {

        executor.execute(
            //AsyncTask.execute(
            () -> {
                List<Note> notes = noteDao.getAll();

                for (Note aNote : notes)
                {
                    tvEntry.post(() ->tvDisplay.append(aNote.getMsg () + "::"
                        + aNote.getParentText () + " " +
                        aNote.getParentBytes ()[0] + " " +
                        aNote.getParentBytes ()[1] +
                        "\n"));
                }
            });
    }
    public void onClickAdd(View view)
    {
        byte[] theBytes = new byte[2];
        theBytes[0] = mCounter++;
        theBytes[1] = mCounter ++;

        Note localNote = new Note();

        localNote.setMsg (tvEntry.getText().toString());
        localNote.setParentText (tvEntry.getText().toString());
        localNote.setParentBytes (theBytes);
        tvDisplay.setText("");

        executor.execute(
        //AsyncTask.execute(
                () -> {
                    noteDao.insertAll(localNote);
                    List<Note> notes = noteDao.getAll();

                    showAllNotes ();
                });

    }
}