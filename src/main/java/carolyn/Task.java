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

    /**
     * Marks the task as done or not done.
     *
     * @param done {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public void mark(boolean done) {
        this.isDone = done;
    }
}
