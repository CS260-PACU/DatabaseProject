package edu.pacificu.cs.cs260.databaseproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NoteDao {

    @Query("Select * FROM note")
    List<Note> getAll();

    @Query("SELECT * From note WHERE nid = :noteid")
    Note findByID(int noteid);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);



}
