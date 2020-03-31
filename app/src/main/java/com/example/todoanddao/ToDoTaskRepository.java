package com.example.todoanddao;

import java.util.List;

class ToDoTaskRepository {
    private static ToDoTaskRepository INSTANCE;
    private final TaskDatabase taskDatabase;
    private final AppExecutor executor;

    public static ToDoTaskRepository getInstance(AppExecutor executor, TaskDatabase database){

        if (INSTANCE == null) {
            INSTANCE = new ToDoTaskRepository(database, executor);
        }
        return INSTANCE;
    }

    private ToDoTaskRepository(TaskDatabase doa, AppExecutor executor){
        this.taskDatabase = doa;
        this.executor = executor;
    }

    public void getListOfTasks(final LoadTasksCallBack callback){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ToDoTask> tasks = taskDatabase.taskDao().getAll();
                executor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onTasksLoaded(tasks);
                    }
                });
            }
        };
        executor.diskIO().execute(runnable);
    }

    public void addTask(final ToDoTask task){
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                taskDatabase.taskDao().insertTask(task);
            }
        };
        executor.diskIO().execute(saveRunnable);
    }

}
