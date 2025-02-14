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

        try{
            Command c = parser.parse(s);
            String type = c.getType();
            Object[] content = c.getArgs();
            if (type.equals("bye")) {
                return ui.sayGoodBye();
            } else if (type.equals("list")) {
                return ui.printTaskList(tasks);
            } else if (type.equals("mark")) {
                Task t = tasks.get((int)content[0]);
                t.mark(true);
                storage.save(tasks);
                return ui.printForMark(t);
            } else if (type.equals("unmark")) {
                Task t = tasks.get((int)content[0]);
                t.mark(false);
                storage.save(tasks);
                return ui.printForUnmark(t);
            } else if (type.equals("delete")) {
                Task t = tasks.get((int)content[0]);
                tasks.delete((int)content[0]);
                storage.save(tasks);
                return ui.printForDelete(t, tasks);
            } else if (type.equals("find")) {
                TaskList found = tasks.find((String)content[0]);
                return ui.printTaskList(found);
            } else if (type.equals("todo")) {
                Task t = new ToDo((String) content[0]);
                tasks.add(t);
                storage.save(tasks);
                return ui.printForAddTask(t, tasks);
            } else if (type.equals("deadline")) {
                Task t = new Deadline((String) content[0], (LocalDate) content[1]);
                tasks.add(t);
                storage.save(tasks);
                return ui.printForAddTask(t, tasks);
            } else {
                Task t = new Event((String) content[0], (LocalDateTime) content[1], (LocalDateTime) content[2]);
                tasks.add(t);
                storage.save(tasks);
                return ui.printForAddTask(t, tasks);
            }
        } catch (CarolynException e) {
            return ui.printException(e);
        }
    }
    public static void main(String[] args){
    }
}
