package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.exceptions.CommandExecutionError;
import duke.parsing.MessageFormat;
import duke.parsing.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Command class for 'delete' keyword.
 * Deletes a task from the task list according to a specified index.
 * <p>
 * Command format: "delete &lt;list_index&gt;"
 */
public class DeleteCmd extends Command {
    private Task task;

    /**
     * Constructor method.
     *
     * @param taskList task list to remove the task from
     * @param lineInput command line input that the user entered
     */
    public DeleteCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Deletes the specified task from the task list. */
    public String execute() throws CommandExecutionError {
        int index = Parser.parseMarkUnmarkDeleteIndex(lineInput);
        this.task = this.taskList.removeTask(index);

        try {
            Storage.saveToFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("The task has disappeared into the night...:\n%s\n%s",
                MessageFormat.indentString(this.task.toString(), 1),
                MessageFormat.numTaskToString(taskList.countTasks()));
    }
}
