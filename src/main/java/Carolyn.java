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
        ArrayList<String> list = new ArrayList<String>();
        String s = scanner.nextLine();
        while (!s.equals("bye")) {
            System.out.print(line);
            if (s.equals("list")) {
                //reference to https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
                //reference to https://stackoverflow.com/questions/20961617/get-the-current-index-of-a-for-each-loop-iterating-an-arraylist
                list.forEach(item -> System.out.println(indent + (list.indexOf(item) + 1) + ". " + item));
            }
            else {
                list.add(s);
                System.out.println(indent + "added: " + s);
            }
            System.out.print(line);
            s = scanner.nextLine();
        }
        System.out.print(Bye);
    }
}
