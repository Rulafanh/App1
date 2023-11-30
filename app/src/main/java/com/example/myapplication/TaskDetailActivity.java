package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Get task details from intent
        task = (Task) getIntent().getSerializableExtra("task");

        // Display task details
        TextView taskNameTextView = findViewById(R.id.taskNameTextView);
        TextView statusTextView = findViewById(R.id.statusTextView);

        taskNameTextView.setText(task.getName());
        statusTextView.setText(task.getStatus());

        // Button to change task status
        Button changeStatusButton = findViewById(R.id.changeStatusButton);
        changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change task status and return to MainActivity
                task.setStatus("Done");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedTask", task);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}

