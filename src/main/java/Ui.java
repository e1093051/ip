import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    String indent = "    ";
    public void sayGoodBye() {
        String bye = "    ____________________________________________________________\n" +
                indent + " Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.print(bye);
    }

    public void greeting() {
        String greeting = "    ____________________________________________________________\n" +
                indent + " Hello! I'm Carolyn\n" +
                indent +" What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.print(greeting);
    }

    public boolean hasInput() {
        return scanner.hasNext();
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printForMark(Task t) {
        System.out.println(indent + " Nice! I've marked this task as done:");
        System.out.println(indent + "   " + t.toString());
    }

    public void printForUnmark(Task t) {
        System.out.println(indent + " OK, I've marked this task as not done yet:");
        System.out.println(indent + "   " + t.toString());
    }

    public void printForDelete(Task t, TaskList list) {
        System.out.println(indent + " Noted. I've removed this task:");
        System.out.println(indent + "   " + t.toString());
        System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
    }

    public void printForAddTask(Task t, TaskList list) {
        System.out.println(indent + " Got it. I've added this task:");
        System.out.println(indent + "   " + t.toString());
        System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
    }

    public void printException(Exception e) {
        System.out.println(indent + e.getMessage());
    }

    public void printTaskList(TaskList list) {
        System.out.print(list.toString());
    }

    public void print(String s) {
        System.out.print(s);
    }
    public void printLine() {
        String line = "    ____________________________________________________________\n";
        System.out.print(line);
    }
}