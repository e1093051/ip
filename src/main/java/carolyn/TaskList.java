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

    public void add(Task t) {
        list.add(t);
    }

    public void delete(int i) {
        list.remove(i);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public TaskList find(String s) {
        TaskList tasks = new TaskList();
        for (int i = 0; i < list.size(); i ++) {
            Task t = list.get(i);
            if (t.getDescription().contains(s)) {
                tasks.add(t);
            }
        }
        return tasks;
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