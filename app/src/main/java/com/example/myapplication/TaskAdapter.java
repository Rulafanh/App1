package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.myapplication.R;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.task_list_item, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView dueDateTextView = convertView.findViewById(R.id.dueDateTextView);
        CheckBox statusCheckBox = convertView.findViewById(R.id.statusCheckBox);

        Task task = tasks.get(position);

        titleTextView.setText(task.getTitle());
        dueDateTextView.setText(task.getDueDate());
        statusCheckBox.setChecked(task.isDone());

        return convertView;
    }
}
