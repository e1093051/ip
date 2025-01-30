import java.util.Scanner;
import java.util.ArrayList;


public class Carolyn {

    public static Task createTask(String s) throws CarolynException{
        String[] command = s.split(" ");
        if (command[0].equals("todo")) {
            int firstSpace = s.indexOf(" ");
            if (firstSpace == -1) {
                throw new CarolynException("no description for todo");
            }
            return (new ToDo(s.substring(firstSpace + 1)));
        }
        else if (command[0].equals(("deadline"))) {
            int firstSpace = s.indexOf(" ");
            int firstSlash = s.indexOf("/");
            int indexOfBy = s.indexOf("by");
            return (new Deadline(s.substring(firstSpace + 1, firstSlash - 1), s.substring(indexOfBy + 3)));
        }
        else if (command[0].equals("event")) {
            int firstSpace = s.indexOf(" ");
            int firstSlash = s.indexOf("/");
            int secondSlash = s.substring(firstSlash + 1).indexOf("/") + firstSlash + 1;
            int indexOfFrom = s.indexOf("/from ");
            int indexOfTo = s.indexOf("/to ");
            String description = s.substring(firstSpace + 1, firstSlash - 1);
            String from = s.substring(indexOfFrom + 6, secondSlash - 1);
            String to = s.substring(indexOfTo + 4);
            return (new Event(description, from, to));
        }
        else {
            throw new CarolynException("invalid task type");
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList list = storage.load();
        Scanner scanner = new Scanner(System.in);
        String indent = "    ";
        String Greeting = "    ____________________________________________________________\n" +
                indent + " Hello! I'm Carolyn\n" +
                indent +" What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String Bye = "    ____________________________________________________________\n" +
                indent + " Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        String line = "    ____________________________________________________________\n";
        System.out.print(Greeting);
        String s = scanner.nextLine();
        while (!s.equals("bye")) {
            System.out.print(line);
            if (s.equals("list")) {
                //reference to https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
                //reference to https://stackoverflow.com/questions/20961617/get-the-current-index-of-a-for-each-loop-iterating-an-arraylist
                System.out.print(list.toString() + "\n");
            }
            //https://www.tutorialspoint.com/java/java_string_matches.htm
            else if (s.matches("mark \\d")) {
                String[] array = s.split(" ");
                //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
                int index = Integer.valueOf(array[1]);
                Task t = list.get(index - 1);
                t.mark(true);
                System.out.println(indent + " Nice! I've marked this task as done:");
                System.out.println(indent + "   " + t.toString());
            }
            else if (s.matches("unmark \\d")) {
                String[] array = s.split(" ");
                //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
                int index = Integer.valueOf(array[1]);
                Task t = list.get(index - 1);
                t.mark(false);
                System.out.println(indent + " OK, I've marked this task as not done yet:");
                System.out.println(indent + "   " + t.toString());
            }
            else if (s.matches("delete \\d")) {
                String[] array = s.split(" ");
                //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
                int index = Integer.valueOf(array[1]);
                Task t = list.get(index - 1);
                list.delete(index - 1);
                System.out.println(indent + " Noted. I've removed this task:");
                System.out.println(indent + "   " + t.toString());
                System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
            }
            else {
                try {
                    Task t = createTask(s);
                    list.add(t);
                    System.out.println(indent + " Got it. I've added this task:");
                    System.out.println(indent + "   " + t.toString());
                    System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
                }
                catch (CarolynException e) {
                    System.out.println(indent + e.getMessage());
                }
            }
            System.out.print(line);
            storage.save(list);
            s = scanner.nextLine();
        }
        System.out.print(Bye);
    }
}
