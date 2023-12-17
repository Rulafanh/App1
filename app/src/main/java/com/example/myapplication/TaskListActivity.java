package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TaskListActivity extends AppCompatActivity {

    private ListView listView;
    private TaskAdapter taskAdapter;
    private TaskManger taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listView = findViewById(R.id.listView);
        taskManager = new TaskManger(this);

        taskAdapter = new TaskAdapter(this, taskManager.getTasks());
        listView.setAdapter(taskAdapter);

        // Handle item click to update task status
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = taskManager.getTasks().get(position);
            taskManager.updateTaskStatus(position, !task.isDone());
            taskAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Task status updated", Toast.LENGTH_SHORT).show();
        });

        // Handle long item click to delete task
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog(position);
            return true;
        });

        // Other initialization and UI handling code
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    taskManager.getTasks().remove(position);
                    taskManager.saveTasks();
                    taskAdapter.notifyDataSetChanged();
                    Toast.makeText(TaskListActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // Method to open NewTaskActivity
    public void openNewTaskActivity(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }
}

