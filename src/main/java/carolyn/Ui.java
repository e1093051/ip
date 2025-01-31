package carolyn;
import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    String indent = "    ";

    /**
     * Displays the goodbye message to the user.
     */
    public void sayGoodBye() {
        String bye = "    ____________________________________________________________\n" +
                indent + " Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.print(bye);
    }

    /**
     * Displays the greeting message to the user upon starting the application.
     */
    public void greeting() {
        String greeting = "    ____________________________________________________________\n" +
                indent + " Hello! I'm Carolyn\n" +
                indent +" What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.print(greeting);
    }

    /**
     * Checks if there is user input available.
     *
     * @return {@code true} if there is input available; {@code false} otherwise.
     */
    public boolean hasInput() {
        return scanner.hasNext();
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The user-entered input as a {@link String}.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param t The {@link Task} that has been marked as done.
     */
    public void printForMark(Task t) {
        System.out.println(indent + " Nice! I've marked this task as done:");
        System.out.println(indent + "   " + t.toString());
    }

    /**
     * Displays a message indicating a task has been unmarked (marked as not done).
     *
     * @param t The {@link Task} that has been unmarked.
     */
    public void printForUnmark(Task t) {
        System.out.println(indent + " OK, I've marked this task as not done yet:");
        System.out.println(indent + "   " + t.toString());
    }

    /**
     * Displays a message indicating a task has been deleted from the task list.
     * <p>
     * Prints a formatted message showing that the specified task has been removed,
     * along with the updated number of tasks in the list.
     * </p>
     *
     * @param t     The {@link Task} that has been deleted.
     * @param list  The {@link TaskList} from which the task was deleted.
     */
    public void printForDelete(Task t, TaskList list) {
        System.out.println(indent + " Noted. I've removed this task:");
        System.out.println(indent + "   " + t.toString());
        System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays a message indicating a new task has been added to the task list.
     * <p>
     * Prints a formatted message showing that the specified task has been added,
     * along with the updated number of tasks in the list.
     * </p>
     *
     * @param t     The {@link Task} that has been added.
     * @param list  The {@link TaskList} to which the task was added.
     */
    public void printForAddTask(Task t, TaskList list) {
        System.out.println(indent + " Got it. I've added this task:");
        System.out.println(indent + "   " + t.toString());
        System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays an exception message to the user.
     *
     * @param e The {@link Exception} containing the error message to display.
     */
    public void printException(Exception e) {
        System.out.println(indent + e.getMessage());
    }

    /**
     * Displays the entire task list to the user.
     *
     * @param list The {@link TaskList} containing tasks to display.
     */
    public void printTaskList(TaskList list) {
        System.out.print(list.toString());
    }

    /**
     * Prints a formatted line to the console.
     */
    public void printLine() {
        String line = "    ____________________________________________________________\n";
        System.out.print(line);
    }
}