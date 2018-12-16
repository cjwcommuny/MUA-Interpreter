package mua.exception;

public class MuaSqrtNegativeException extends MuaException {
    public MuaSqrtNegativeException() {
        super("Apply sqrt to a negative number", Level.ERROR);
    }
}
