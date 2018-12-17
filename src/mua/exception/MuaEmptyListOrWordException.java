package mua.exception;

public class MuaEmptyListOrWordException extends MuaException {
    public MuaEmptyListOrWordException() {
        super("list or word is empty.", Level.ERROR);
    }
}
