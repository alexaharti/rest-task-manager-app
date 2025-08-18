package pixel.academy.rest_task_manager_app.entity;

import java.util.ArrayList;
import java.util.List;

public class StoreTasks {

    private List<Task> theTasks = new ArrayList<>();

    public StoreTasks() {

        theTasks.add(new Task(1, "Write an essay", true));
        theTasks.add(new Task(2, "Solve a LeetCode", false));
        theTasks.add(new Task(3, "Learn italian", false));
        theTasks.add(new Task(4, "Read a book", true));
        theTasks.add(new Task(5, "Feed the cat", true));
    }

    public List<Task> getTasks() {

        return theTasks;
    }

    public Task getTask(int taskId)  {

        if ((taskId >= theTasks.size()) || (taskId <= 0)) {

            throw new ResourceNotFoundException("Task id not found.");
        }

        return theTasks.get(taskId);
    }
}
