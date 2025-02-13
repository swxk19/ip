package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.exceptions.CommandExecutionError;
import duke.parsing.MessageFormat;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Command class for 'todo' command keyword.
 * Creates a new ToDo task and adds it to the task list.
 * <p>
 * Command format: "todo &lt;task_name&gt;"
 */
public class ToDoCmd extends Command {
    private Task toDo;

    /**
     * Constructor method.
     *
     * @param taskList task list to add the ToDo task to
     * @param lineInput command line input that the user entered
     */
    public ToDoCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Adds the ToDo task to the task list. */
    public String execute() throws CommandExecutionError {
        this.toDo = ToDo.create(this.lineInput);
        taskList.add(this.toDo);

        try {
            Storage.saveToFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("I'll remember this task:\n%s\n%s",
                MessageFormat.indentString(this.toDo.toString(), 1),
                MessageFormat.numTaskToString(taskList.countTasks()));
    };
}
