package carolyn;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void delete(int i) {
        tasks.remove(i);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String indent = "    ";
        String s = "";
        for (int i = 0; i < tasks.size(); i ++) {
            Task item = tasks.get(i);
            String line = indent + (i + 1) + "." + item.toString() + "\n";
            s += line;
        }
        return s;
    }
}