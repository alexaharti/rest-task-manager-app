package pixel.academy.rest_task_manager_app.entity;

import org.springframework.stereotype.Component;
import pixel.academy.rest_task_manager_app.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreTasks {

    private List<Task> theTasks = new ArrayList<>();

    public StoreTasks() {

        theTasks.add(new Task(0, "Write an essay", true));
        theTasks.add(new Task(1, "Solve a LeetCode", false));
        theTasks.add(new Task(2, "Learn italian", false));
        theTasks.add(new Task(3, "Read a book", true));
        theTasks.add(new Task(4, "Feed the cat", true));
        theTasks.add(new Task(5, "Go to the GYM", false));
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
