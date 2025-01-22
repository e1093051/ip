import java.util.Scanner;
public class Carolyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String Greeting = "____________________________________________________________\n" +
                " Hello! I'm Carolyn\n" +
                " What can I do for you?\n";
        String Bye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        String line = "____________________________________________________________\n";
        System.out.print(Greeting);
        String s = scanner.nextLine();
        while (!s.equals("bye")) {
            System.out.println(line + s + "\n" + line);
            s = scanner.nextLine();
        }
        System.out.print(Bye);
    }
}
