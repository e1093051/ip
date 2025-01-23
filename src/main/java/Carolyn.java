import java.util.Scanner;
import java.util.ArrayList;


public class Carolyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String indent = "    ";
        String Greeting = "    ____________________________________________________________\n" +
                indent + " Hello! I'm Carolyn\n" +
                indent +" What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String Bye = "    ____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        String line = "    ____________________________________________________________\n";
        System.out.print(Greeting);
        ArrayList<Task> list = new ArrayList<Task>();
        String s = scanner.nextLine();
        while (!s.equals("bye")) {
            System.out.print(line);
            if (s.equals("list")) {
                //reference to https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
                //reference to https://stackoverflow.com/questions/20961617/get-the-current-index-of-a-for-each-loop-iterating-an-arraylist
                list.forEach(item -> System.out.println(indent + (list.indexOf(item) + 1) + ".[" + item.getStatusIcon() + "]" + item.getDescription()));
            }
            //https://www.tutorialspoint.com/java/java_string_matches.htm
            else if (s.matches("mark \\d")) {
                String[] array = s.split(" ");
                //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
                int index = Integer.valueOf(array[1]);
                Task t = list.get(index);
                t.mark(true);
                System.out.println(indent + " Nice! I've marked this task as done:");
                System.out.println(indent + "   [X] " + t.getDescription());
            }
            else if (s.matches("unmark \\d")) {
                String[] array = s.split(" ");
                //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
                int index = Integer.valueOf(array[1]);
                Task t = list.get(index);
                t.mark(false);
                System.out.println(indent + " OK, I've marked this task as not done yet:");
                System.out.println(indent + "   [ ] " + t.getDescription());
            }
            else {
                list.add(new Task(s));
                System.out.println(indent + "added: " + s);
            }
            System.out.print(line);
            s = scanner.nextLine();
        }
        System.out.print(Bye);
    }
}
