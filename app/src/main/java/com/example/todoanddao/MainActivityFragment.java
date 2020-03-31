package com.example.todoanddao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    public static final String TAG = "MainActivityFragment";
    private MainViewModel viewModel;
    private View view;
    private TaskAdapter adapter;
    private MainViewModelFactory factory;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_main, container, false);

        factory = new MainViewModelFactory(ToDoTaskRepository.getInstance(new AppExecutor(), TaskDatabase.getInstance(getContext())));
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);


        setupList();
        setUpObservers();


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        return view;
    }

    private void setUpObservers() {
        viewModel.getToDoTaskList().observe(getViewLifecycleOwner(), new Observer<List<ToDoTask>>() {
            @Override
            public void onChanged(@Nullable List<ToDoTask> toDoTasks) {
                adapter.setList(toDoTasks);
            }
        });
    }


    private void openDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_task_dialog, null);

        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.setTitle("Add ToDo Task");
        dialog.setView(dialogView);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                EditText title = dialogView.findViewById(R.id.editTextTitle);
                EditText desc = dialogView.findViewById(R.id.editTextDesc);

                viewModel.addTaskToUi(title.getText().toString(), desc.getText().toString());
            }
        });
        dialog.show();

    }

    private void setupList() {
        RecyclerView list = view.findViewById(R.id.todolistview);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        list.setHasFixedSize(true);

        adapter = new TaskAdapter(new ArrayList<ToDoTask>());
        list.setAdapter(adapter);
    }

}

