import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;


public class Carolyn {

    public static void main(String[] args) {

        Parser parser = new Parser();
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
        try{
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                System.out.print(line);
                Command c = parser.parse(s);
                String type = c.getType();
                Object[] content = c.getArgs();
                if (type.equals("bye")) {
                    System.out.print(Bye);
                }
                else if (type.equals("list")) {
                    list.forEach(item -> System.out.println(indent + (list.indexOf(item) + 1) + "." + item.toString()));
                }
                else if (type.equals("mark")) {
                    Task t = list.get((int)content[0]);
                    t.mark(true);
                    System.out.println(indent + " Nice! I've marked this task as done:");
                    System.out.println(indent + "   " + t.toString());
                } else if (type.equals("unmark")) {
                    Task t = list.get((int)content[0]);
                    t.mark(false);
                    System.out.println(indent + " OK, I've marked this task as not done yet:");
                    System.out.println(indent + "   " + t.toString());
                } else if (type.equals("delete")) {
                    Task t = list.get((int)content[0]);
                    list.delete((int)content[0]);
                    System.out.println(indent + " Noted. I've removed this task:");
                }
                else {
                    System.out.println(indent + " Got it. I've added this task:");
                    if (type.equals("todo")) {
                        Task t = new ToDo((String)content[0]);
                        list.add(t);
                        System.out.println(indent + "   " + t.toString());
                    }
                    else if (type.equals("deadline")) {
                        Task t = new Deadline((String)content[0], (LocalDate) content[1]);
                        list.add(t);
                        System.out.println(indent + "   " + t.toString());
                    }
                    else {
                        Task t = new Event((String)content[0], (LocalDateTime) content[1], (LocalDateTime) content[2]);
                        list.add(t);
                        System.out.println(indent + "   " + t.toString());
                    }
                    System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
                }
                System.out.print(line);
            }
        } catch (CarolynException e){
            System.out.println(indent + e.getMessage());
            storage.save(list);

        }
    }
}
