package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText dueDateEditText;
    private TaskManger taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);

        taskManager = new TaskManger(this);
    }

    // Method to save a new task
    public void saveTask(View view) {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String dueDate = dueDateEditText.getText().toString();

        if (!TextUtils.isEmpty(title)) {
            Task newTask = new Task(title, description, dueDate, false);
            taskManager.addTask(newTask);
            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}

