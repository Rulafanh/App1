package com.example.myapplication;

import java.io.Serializable;

public class Task implements Serializable {
    String title;
    String description;
    String dueDate;
    boolean isDone;

    public Task(String title, String description, String dueDate, boolean isDone) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return isDone;
    }
}

