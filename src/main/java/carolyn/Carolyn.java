package carolyn;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Carolyn {
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    public Carolyn() {
        this.parser = new Parser();
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = storage.load();
    }

    /**
     * Runs the main program loop, processing user input and executing commands.
     * <p>
     * This method:
     * <ul>
     *     <li>Initializes a {@link Parser}, {@link Storage}, and {@link TaskList}.</li>
     *     <li>Loads previously saved tasks from storage.</li>
     *     <li>Continuously reads user input, processes commands, and updates the task list.</li>
     *     <li>Handles different command types: listing tasks, marking/unmarking tasks, deleting tasks, and adding new tasks.</li>
     *     <li>Saves changes to storage after each modification.</li>
     *     <li>Exits when the "bye" command is received.</li>
     * </ul>
     * </p>
     */
    public String getResponse(String s) {
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList tasks = storage.load();
        assert tasks != null : "a task list should have been loaded, empty or not";
        try{
            Command c = parser.parse(s);
            String type = c.getType();
            Object[] argsForCommand = c.getArgs();
            switch (type) {
                case "bye" -> {
                    return ui.sayGoodBye();
                }
                case "list" -> {
                    return ui.printTaskList(tasks);
                }
                case "mark" -> {
                    Task t = tasks.get((int) argsForCommand[0]);
                    t.mark(true);
                    storage.save(tasks);
                    return ui.printForMark(t);
                }
                case "unmark" -> {
                    Task t = tasks.get((int) argsForCommand[0]);
                    t.mark(false);
                    storage.save(tasks);
                    return ui.printForUnmark(t);
                }
                case "delete" -> {
                    Task t = tasks.get((int) argsForCommand[0]);
                    tasks.delete((int) argsForCommand[0]);
                    storage.save(tasks);
                    return ui.printForDelete(t, tasks);
                }
                case "find" -> {
                    TaskList found = tasks.find((String) argsForCommand[0]);
                    return ui.printTaskList(found);
                }
                case "tag" -> {
                    Task t = tasks.get((int) argsForCommand[0]);
                    String tagString = (String) argsForCommand[1];
                    t.tag(tagString);
                    storage.save(tasks);
                    return ui.printForAddTag(t, tagString);
                }
                case "todo" -> {
                    Task t = new ToDo((String) argsForCommand[0]);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.printForAddTask(t, tasks);
                }
                case "deadline" -> {
                    Task t = new Deadline((String) argsForCommand[0], (LocalDate) argsForCommand[1]);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.printForAddTask(t, tasks);
                }
                default -> {
                    Task t = new Event((String) argsForCommand[0], (LocalDateTime) argsForCommand[1], (LocalDateTime) argsForCommand[2]);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.printForAddTask(t, tasks);
                }
            }
        } catch (CarolynException e) {
            return ui.printException(e);
        }
    }
    public static void main(String[] args){
    }
}
