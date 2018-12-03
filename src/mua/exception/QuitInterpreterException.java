package mua.exception;

import java.util.List;

public class QuitInterpreterException extends MuaException {
    private List<String> results;

    public void setResults(List<String> results) {
        this.results = results;
    }

    public QuitInterpreterException(List<String> results) {
        super("Quit Interpreter");
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}
