package com.example.todoanddao;

import java.util.List;

public interface LoadTasksCallBack {

    void onTasksLoaded(List<ToDoTask> tasks);
}
