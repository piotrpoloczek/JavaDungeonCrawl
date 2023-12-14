package com.codecool.dungeoncrawl.logic.tasks;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Journal implements Serializable {
    private List<Task> actualTasks = new ArrayList<>();
    @Getter
    private List<Task> finishedTask = new ArrayList<>();

    public Journal() {

    }

    public void addTaskToTheJournal(Task task) {
        actualTasks.add(task);
    }

    public boolean taskAlreadyInJournal(String taskName) {
        return actualTasks.stream().anyMatch(task -> task.getName().equals(taskName));
    }

    public Task moveTaskToFinishedList(String taskName) {
        Task task = actualTasks.stream().filter(t -> t.getName().equals(taskName)).findFirst().get();
        finishedTask.add(task);

        return task;
    }
}
