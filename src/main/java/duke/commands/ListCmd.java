package duke.commands;

import duke.Duke;
import duke.tasks.TaskList;

/**
 * Command class for 'list' command keyword.
 * Output to UI all tasks currently in list
 * <p>
 * Command format: "list"
 */
public class ListCmd extends Command {

    /**
     * Constructor method.
     * @param taskList Task list to output to UI
     * @param lineInput Command line input that the user entered
     */
    public ListCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    // Executes UI reply
    public void execute(Duke duke) {
        duke.sendResponse("Here are the tasks in your list:\n" + taskList.toString());
    }
}
