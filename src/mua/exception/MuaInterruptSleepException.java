package mua.exception;

public class MuaInterruptSleepException extends MuaException {
    public MuaInterruptSleepException() {
        super("Wait operator is interrupted.", Level.ERROR);
    }
}
