package com.example.todoanddao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


/**
 * Created by Samih on 21-Jun-18.
 */
@Dao
public interface TodoDao {

    @Query("SELECT * FROM ToDoTask")
    List<ToDoTask> getAll();

    @Insert
    void insertTask(ToDoTask task);


}