package carolyn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;


public class Carolyn {
    Parser parser;
    Storage storage;
    Ui ui;
    TaskList list;

    public Carolyn() {
        this.parser = new Parser();
        this.storage = new Storage();
        this.ui = new Ui();
        this.list = storage.load();
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
        TaskList list = storage.load();
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
                }
                else if (type.equals("list")) {
                    ui.printTaskList(list);
                }
                else if (type.equals("mark")) {
                    Task t = list.get((int)content[0]);
                    t.mark(true);
                    ui.printForMark(t);
                } else if (type.equals("unmark")) {
                    Task t = list.get((int)content[0]);
                    t.mark(false);
                    ui.printForUnmark(t);
                } else if (type.equals("delete")) {
                    Task t = list.get((int)content[0]);
                    list.delete((int)content[0]);
                    ui.printForDelete(t, list);
                }
                else {
                    if (type.equals("todo")) {
                        Task t = new ToDo((String)content[0]);
                        list.add(t);
                        ui.printForAddTask(t, list);
                    }
                    else if (type.equals("deadline")) {
                        Task t = new Deadline((String)content[0], (LocalDate) content[1]);
                        list.add(t);
                        ui.printForAddTask(t, list);
                    }
                    else {
                        Task t = new Event((String)content[0], (LocalDateTime) content[1], (LocalDateTime) content[2]);
                        list.add(t);
                        ui.printForAddTask(t, list);
                    }
                }
                storage.save(list);
                ui.printLine();
            }
        } catch (CarolynException e){
            ui.printException(e);
        }
    }
    public static void main(String[] args){
        new Carolyn().run();
    }
}
