package duke;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.parsing.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Methods related to saving/loading data to/from files.
 */
public class Storage {

    private static String strDir = "./data";
    private static String fileName = "./data/duke.txt";

    /**
     * Saves a task list to a file.
     *
     * @param tasks Task list to save.
     * @throws IOException Specified file not found.
     */
    public static void saveToFile(TaskList tasks) throws IOException {
        // create directory
        Path path = Paths.get(strDir);
        Files.createDirectories(path);

        // output string to file
        PrintWriter out = new PrintWriter(fileName);
        out.print(tasks.toStringSave());
        out.close();

    }

    /**
     * Loads a task list from a file.
     *
     * @param tasks Task list to load into.
     * @throws IOException Specified file not found.
     */
    public static void loadFromFile(TaskList tasks) throws IOException {
        Path fileNamePath = Path.of(fileName);
        String strData = Files.readString(fileNamePath);

        String[] strTasks = strData.split("\n");
        for (String strTask : strTasks) {
            if (strTask.length() <= 1) {
                continue;
            }
            Task loadedTask = Parser.parseLoadedTask(strTask);
            tasks.add(loadedTask);
        }
    }

}
