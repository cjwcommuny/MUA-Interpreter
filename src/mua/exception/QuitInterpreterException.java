package mua.exception;

import java.util.List;

public class QuitInterpreterException extends MuaException {
    public QuitInterpreterException() {
        super("quit", Level.MESSAGE);
    }
}
