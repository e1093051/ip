public class Command {
    String type;
    Object[] args;

    public Command(String type, Object[] args) {
        this.type = type;
        this.args = args;
    }

    public String getType() {
        return this.type;
    }

    public Object[] getArgs() {
        return this.args;
    }
}