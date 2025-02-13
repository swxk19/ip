package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.CommandExecutionError;
import duke.exceptions.ListIndexOutOfRange;


/**
 * Wrapper class for list to contain tasks
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Represent list of tasks as a string.
     *
     * @return String representation of task list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            boolean addNewLine = index != 0;
            result.append((addNewLine ? "\n" : "") + (index + 1) + ". " + this.tasks.get(index).toString());
        }
        return result.toString();
    }

    /**
     * Represent list of tasks as a string in a format for saving to a file.
     *
     * @return String representation of task list for saving
     */
    public String toStringSave() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            boolean addNewLine = index != 0;
            result.append((addNewLine ? "\n" : "") + (index + 1) + ". " + this.tasks.get(index).toStringSave());
        }
        return result.toString();
    }

    /**
     * Gets number of tasks in the list.
     *
     * @return Number of tasks
     */
    public int countTasks() {
        return this.tasks.size();
    }

    /**
     * Removes a task the list at a specified index.
     *
     * @param index Index of task to remove
     * @return Removed task
     */
    public Task removeTask(int index) throws CommandExecutionError {
        int sizeBeforeRemove = this.countTasks();
        Task removedTask;
        try {
            removedTask = this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexOutOfRange();
        }
        assert sizeBeforeRemove - this.countTasks() == 1;
        return removedTask;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add
     */
    public void add(Task task) {
        int sizeBeforeAdd = this.countTasks();
        this.tasks.add(task);
        assert this.countTasks() - sizeBeforeAdd == 1;
    }

    /**
     * Gets the task from the list at a specified index.
     *
     * @param index Index of task to get.
     * @return Task to get
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Searches for tasks that contains the search keyword(s)
     * @param keywords Search query
     * @return A task list containing tasks which contain the search keyword
     */
    public TaskList find(String... keywords) {
        TaskList result = new TaskList();
        tasks.stream().forEach(task -> {
            for (String keyword : keywords) {
                if (task.taskName.contains(keyword)) {
                    result.add(task);
                }
            }
        });
        return result;
    }

    /**
     * Gets size of the task list.
     *
     * @return size of the task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets due date of task.
     *
     * @return due date of task
     */
    public TaskList viewSched(LocalDate date) {
        TaskList result = new TaskList();
        for (Task task : this.tasks) {
            LocalDate endDate = task.getEndDate();
            if (endDate != null && (date.equals(endDate) || date.isAfter(endDate))) {
                result.add(task);
            }
        }
        return result;
    }
}
