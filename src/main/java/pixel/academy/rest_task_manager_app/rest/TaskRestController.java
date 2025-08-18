package pixel.academy.rest_task_manager_app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pixel.academy.rest_task_manager_app.entity.StoreTasks;
import pixel.academy.rest_task_manager_app.entity.Task;
import pixel.academy.rest_task_manager_app.exception.ResourceNotFoundException;
import pixel.academy.rest_task_manager_app.exception.TaskExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    private StoreTasks tasks;

    public TaskRestController(StoreTasks tasks) {
        this.tasks = tasks;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {

        return tasks.getTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTaskById(@PathVariable int taskId) {

        if ((taskId >= tasks.getTasks().size()) || (taskId <= 0)) {
            throw new ResourceNotFoundException("Task id not found - " + taskId);
        }

        return tasks.getTasks().get(taskId);
    }

    @ExceptionHandler
    public ResponseEntity<TaskExceptionHandler> handleException(ResourceNotFoundException ex) {

        TaskExceptionHandler error = new TaskExceptionHandler();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TaskExceptionHandler> handleException(Exception ex) {

        TaskExceptionHandler error = new TaskExceptionHandler();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Error. Something went wrong.");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
