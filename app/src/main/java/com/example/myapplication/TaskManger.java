package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManger {
    private static final String PREF_NAME = "TaskAppPrefs";
    private static final String TASKS_KEY = "tasks";

    private static List<Task> tasks;
    private SharedPreferences preferences;
    private Gson gson;

    public TaskManger(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadTasks();
    }

    private void loadTasks() {
        String tasksJson = preferences.getString(TASKS_KEY, null);
        if (tasksJson != null) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            tasks = gson.fromJson(tasksJson, type);
        } else {
            tasks = new ArrayList<>();
        }
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public void updateTaskStatus(int position, boolean isDone) {
        tasks.get(position).isDone = isDone;
        saveTasks();
    }

    public void saveTasks() {
        SharedPreferences.Editor editor = preferences.edit();
        String tasksJson = gson.toJson(tasks);
        editor.putString(TASKS_KEY, tasksJson);
        editor.apply();
    }
}


