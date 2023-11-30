package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TASKS_PREFS = "tasks_prefs";
    private static final String TASKS_KEY = "tasks_key";
    private List<Task> tasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load tasks from SharedPreferences
        tasks = loadTasks();

        // Initialize ListView and adapter
        ListView listView = findViewById(R.id.listView);
        taskAdapter = new TaskAdapter(this, tasks);
        listView.setAdapter(taskAdapter);

        // Set item click listener to go to TaskDetailActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task selectedTask = tasks.get(position);
                Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent.putExtra("task", selectedTask);
                startActivityForResult(intent, 1);
            }
        });
    }

    // Handle result from TaskDetailActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Update task status
            Task updatedTask = (Task) data.getSerializableExtra("updatedTask");
            int position = tasks.indexOf(updatedTask);
            tasks.set(position, updatedTask);
            taskAdapter.notifyDataSetChanged();
            saveTasks(); // Save updated tasks to SharedPreferences
        }
    }

    // Load tasks from SharedPreferences
    private List<Task> loadTasks() {
        SharedPreferences prefs = getSharedPreferences(TASKS_PREFS, MODE_PRIVATE);
        String tasksJson = prefs.getString(TASKS_KEY, null);

        if (tasksJson != null) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            return new Gson().fromJson(tasksJson, type);
        } else {
            return new ArrayList<>();
        }
    }

    // Save tasks to SharedPreferences
    private void saveTasks() {
        SharedPreferences prefs = getSharedPreferences(TASKS_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String tasksJson = new Gson().toJson(tasks);
        editor.putString(TASKS_KEY, tasksJson);
        editor.apply();
    }

    // Handle the onClick event for adding a new task
    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, 1);
    }
}
