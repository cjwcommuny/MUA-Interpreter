package mua.exception;

public class MuaValidInputException extends MuaException {
    public MuaValidInputException() {
        super("invalid input.", Level.ERROR);
    }
}
