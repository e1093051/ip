package carolyn;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return (description);
    }

    public String getTagsList() {
        String s = "";
        for (String item : tags) {
            s += "#" + item + " ";
        }
        return s;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description + " " + this.getTagsList());
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    public void tag(String tagString) {
        this.tags.add(tagString);
    }
}
