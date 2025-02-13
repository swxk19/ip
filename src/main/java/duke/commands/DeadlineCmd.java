package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.exceptions.CommandExecutionError;
import duke.parsing.MessageFormat;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'deadline' command keyword.
 * Creates a new Deadline task and adds it to the task list.
 * Command input must be accompanied by a '/by' keyword to indicate the due date of the task.
 * <p>
 * Command format: "deadline &lt;task_name&gt; /by &lt;due_date&gt;"
 */
public class DeadlineCmd extends Command {
    private Task deadline;

    /**
     * Constructor method.
     *
     * @param taskList task list to add the Deadline task to
     * @param lineInput command line input that the user entered
     */
    public DeadlineCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Adds the Deadline task to the task list. */
    public String execute() throws CommandExecutionError {
        this.deadline = Deadline.create(this.lineInput);
        taskList.add(this.deadline);

        try {
            Storage.saveToFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("Well, well... another task to the list:\n%s\n%s",
                MessageFormat.indentString(this.deadline.toString(), 1),
                MessageFormat.numTaskToString(taskList.countTasks()));
    }
}
