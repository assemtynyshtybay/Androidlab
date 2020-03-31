package com.example.todoanddao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

class MainViewModel extends ViewModel {

    private ToDoTaskRepository toDoTaskRepository;
    private MutableLiveData<List<ToDoTask>> taskList;


    public MainViewModel(ToDoTaskRepository taskrepo){
        toDoTaskRepository = taskrepo;
    }

    public LiveData<List<ToDoTask>> getToDoTaskList(){
        if(taskList == null){
            taskList = new MutableLiveData<>();
            loadTasks();
        }
        return taskList;
    }

    private void loadTasks() {
        toDoTaskRepository.getListOfTasks(new LoadTasksCallBack() {
            @Override
            public void onTasksLoaded(List<ToDoTask> tasks) {
                taskList.setValue(tasks);
            }
        });
    }

    public void addTaskToUi(String title, String desc) {
        toDoTaskRepository.addTask(new ToDoTask(title, desc));
    }
}
