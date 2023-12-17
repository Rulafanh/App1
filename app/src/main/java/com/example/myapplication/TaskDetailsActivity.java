package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Get the task position from the intent
        int taskPosition = getIntent().getIntExtra("taskPosition", -1);

        if (taskPosition != -1) {
            // Retrieve the task from the TaskManager based on the position
            Task task = TaskManger.getTasks().get(taskPosition);

            // Populate UI elements with task details
            TextView titleTextView = findViewById(R.id.titleTextView);
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            TextView dueDateTextView = findViewById(R.id.dueDateTextView);
            TextView statusTextView = findViewById(R.id.statusTextView);

            titleTextView.setText("Title: " + task.getTitle());
            descriptionTextView.setText("Description: " + task.getDescription());
            dueDateTextView.setText("Due Date: " + task.getDueDate());
            statusTextView.setText("Status: " + (task.isDone() ? "Done" : "Due"));
        }
    }
}

