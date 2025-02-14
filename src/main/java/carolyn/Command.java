package carolyn;

public class Command {
    protected String type;
    protected Object[] args;

    public Command(String type, Object[] args) {
        this.type = type;
        this.args = args;
    }

    public String getType() {
        assert this.type.length() > 0 : "For every command, type should be specified";
        return this.type;
    }

    public Object[] getArgs() {
        return this.args;
    }
}