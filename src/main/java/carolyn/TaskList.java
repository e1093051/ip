package carolyn;
import java.util.ArrayList;
import java.io.Serializable;
public class TaskList implements Serializable{
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The {@link Task} to be added to the list.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param i The zero-based index of the {@link Task} to be removed..
     */
    public void delete(int i) {
        list.remove(i);
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param i The zero-based index of the {@link Task} to retrieve.
     * @return The {@link Task} at the specified index.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of {@link Task} objects currently in the list.
     */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        String indent = "    ";
        String s = "";
        for (int i = 0; i < list.size(); i ++) {
            Task item = list.get(i);
            String line = indent + (i + 1) + "." + item.toString() + "\n";
            s += line;
        }
        return s;
    }
}