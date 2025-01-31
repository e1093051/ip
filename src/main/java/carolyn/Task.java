package carolyn;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return (description);
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public void mark(boolean done) {
        this.isDone = done;
    }
}
