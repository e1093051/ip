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
    public void run () {
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList tasks = storage.load();
        ui.greeting();
        try{
            while (ui.hasInput()) {
                String s = ui.readInput();
                ui.printLine();
                Command c = parser.parse(s);
                String type = c.getType();
                Object[] content = c.getArgs();
                if (type.equals("bye")) {
                    ui.sayGoodBye();
                    break;
                } else if (type.equals("list")) {
                    ui.printTaskList(tasks);
                } else if (type.equals("mark")) {
                    Task t = tasks.get((int)content[0]);
                    t.mark(true);
                    ui.printForMark(t);
                } else if (type.equals("unmark")) {
                    Task t = tasks.get((int)content[0]);
                    t.mark(false);
                    ui.printForUnmark(t);
                } else if (type.equals("delete")) {
                    Task t = tasks.get((int)content[0]);
                    tasks.delete((int)content[0]);
                    ui.printForDelete(t, tasks);
                } else if (type.equals("find")) {
                    TaskList found = tasks.find((String)content[0]);
                    ui.printTaskList(found);
                } else {
                    if (type.equals("todo")) {
                        Task t = new ToDo((String)content[0]);
                        tasks.add(t);
                        ui.printForAddTask(t, tasks);
                    } else if (type.equals("deadline")) {
                        Task t = new Deadline((String)content[0], (LocalDate) content[1]);
                        tasks.add(t);
                        ui.printForAddTask(t, tasks);
                    } else {
                        Task t = new Event((String)content[0], (LocalDateTime) content[1], (LocalDateTime) content[2]);
                        tasks.add(t);
                        ui.printForAddTask(t, tasks);
                    }
                }
                storage.save(tasks);
                ui.printLine();
            }
        } catch (CarolynException e) {
            ui.printException(e);
        }
    }
    public static void main(String[] args){
        new Carolyn().run();
    }
}
