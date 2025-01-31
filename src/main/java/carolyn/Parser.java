package carolyn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//reference to https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
//reference to https://stackoverflow.com/questions/20961617/get-the-current-index-of-a-for-each-loop-iterating-an-arraylist
//https://www.tutorialspoint.com/java/java_string_matches.htm
//https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/

public class Parser {
    /**
     * Parses a user input string and converts it into a {@link Command} object.
     *
     * @param s The input command string entered by the user.
     * @return A {@link Command} object representing the parsed command and its associated arguments.
     * @throws CarolynException If the input command is invalid, incomplete, or does not match any supported command patterns.
     */
    public Command parse(String s) throws CarolynException {
        Object[] args = new Object[5];
        if (s.equals("list")) {
            return new Command("list", args);
        } else if (s.equals("bye")) {
            return new Command("bye", args);
        } else if (s.matches("mark \\d")) {
            String[] array = s.split(" ");
            int index = Integer.valueOf(array[1]);
            args[0] = index - 1;
            return new Command("mark", args);
        } else if (s.matches("unmark \\d")) {
            String[] array = s.split(" ");
            int index = Integer.valueOf(array[1]);
            args[0] = index - 1;
            return new Command("unmark", args);
        } else if (s.matches("delete \\d")) {
            String[] array = s.split(" ");
            int index = Integer.valueOf(array[1]);
            args[0] = index - 1;
            return new Command("delete", args);
        } else {
            String[] command = s.split(" ");
            if (command[0].equals("find")) {
                int firstSpace = s.indexOf(" ");
                args[0] = s.substring(firstSpace + 1);
                return new Command("find", args);
            }
            else {
                if (command[0].equals("todo")) {
                    int firstSpace = s.indexOf(" ");
                    if (firstSpace == -1) {
                        throw new CarolynException("no description for todo");
                    }
                    args[0] = s.substring(firstSpace + 1);
                    return new Command("todo", args);
                }
                // of this form "deadline return book /by YYYY-MM-DD"
                else if (command[0].equals(("deadline"))) {
                    int firstSpace = s.indexOf(" ");
                    int firstSlash = s.indexOf("/");
                    int indexOfBy = s.indexOf("by");
                    LocalDate date = LocalDate.parse(s.substring(indexOfBy + 3));
                    args[0] = s.substring(firstSpace + 1, firstSlash - 1);
                    args[1] = date;
                    return new Command("deadline", args);
                }
                // event project meeting /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM
                else if (command[0].equals("event")) {
                    int firstSpace = s.indexOf(" ");
                    int firstSlash = s.indexOf("/");
                    int secondSlash = s.substring(firstSlash + 1).indexOf("/") + firstSlash + 1;
                    int indexOfFrom = s.indexOf("/from ");
                    int indexOfTo = s.indexOf("/to ");
                    String description = s.substring(firstSpace + 1, firstSlash - 1);
                    args[0] = description;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String from = s.substring(indexOfFrom + 6, secondSlash - 1);
                    String to = s.substring(indexOfTo + 4);
                    args[1] = LocalDateTime.parse(from, formatter);
                    args[2] = LocalDateTime.parse(to, formatter);
                    return new Command("event", args);
                } else {
                    throw new CarolynException("invalid task type");
                }
            }
        }
    }
}