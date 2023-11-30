package com.example.myapplication;

// AddTaskActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    // Handle the onClick event for adding a new task
    public void addTask(View view) {
        EditText taskNameEditText = findViewById(R.id.taskNameEditText);
        String taskName = taskNameEditText.getText().toString().trim();

        if (!taskName.isEmpty()) {
            Task newTask = new Task(taskName, "Due");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedTask", newTask);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}

