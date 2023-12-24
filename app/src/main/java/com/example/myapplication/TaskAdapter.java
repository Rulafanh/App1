package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private final Context context;
    private final List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.activity_task_list, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_task_list, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView dueDateTextView = convertView.findViewById(R.id.dueDateTextView);
        CheckBox statusCheckBox = convertView.findViewById(R.id.statusTextView);

        Task task = tasks.get(position);

        titleTextView.setText(task.getTitle());
        dueDateTextView.setText(task.getDueDate());
        statusCheckBox.setChecked(task.isDone());

        return convertView;
    }
}
