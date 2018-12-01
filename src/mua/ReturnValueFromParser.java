package mua;

import java.util.List;

public class ReturnValueFromParser {
    private List<String> results;
    private InterpreterRunningCommand command;

    public ReturnValueFromParser(List<String> results, InterpreterRunningCommand command) {
        this.results = results;
        this.command = command;
    }

    public List<String> getResults() {
        return results;
    }

    public InterpreterRunningCommand getCommand() {
        return command;
    }
}
