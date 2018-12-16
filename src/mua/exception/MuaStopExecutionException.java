package mua.exception;

public class MuaStopExecutionException extends MuaException {
    public MuaStopExecutionException() {
        super("stop", Level.MESSAGE);
    }
}
