package com.example.todoanddao;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final ToDoTaskRepository repo;

    public MainViewModelFactory(ToDoTaskRepository toDoTaskRepository){
        repo = toDoTaskRepository;
    }

    @NonNull
    @Override
    public MainViewModel create(@NonNull Class modelClass) {
        return new MainViewModel(repo);
    }
}
