package carolyn;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    String INDENT = "    ";
    public void sayGoodBye() {
        String bye = "    ____________________________________________________________\n"
                + INDENT + " Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.print(bye);
    }

    public void greeting() {
        String greeting = "    ____________________________________________________________\n"
                + INDENT + " Hello! I'm Carolyn\n"
                + INDENT +" What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.print(greeting);
    }

    public boolean hasInput() {
        return scanner.hasNext();
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printForMark(Task t) {
        System.out.println(INDENT + " Nice! I've marked this task as done:");
        System.out.println(INDENT + "   " + t.toString());
    }

    public void printForUnmark(Task t) {
        System.out.println(INDENT + " OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "   " + t.toString());
    }

    public void printForDelete(Task t, TaskList list) {
        System.out.println(INDENT + " Noted. I've removed this task:");
        System.out.println(INDENT + "   " + t.toString());
        System.out.println(INDENT + " Now you have " + list.size() + " tasks in the list.");
    }

    public void printForAddTask(Task t, TaskList list) {
        System.out.println(INDENT + " Got it. I've added this task:");
        System.out.println(INDENT + "   " + t.toString());
        System.out.println(INDENT + " Now you have " + list.size() + " tasks in the list.");
    }

    public void printException(Exception e) {
        System.out.println(INDENT + e.getMessage());
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