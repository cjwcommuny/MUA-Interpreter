package mua.exception;

public class MuaRepeatNumberNegativeException extends MuaException {
    public MuaRepeatNumberNegativeException() {
        super("The repeat number of `repeat` operator is negative.", Level.ERROR);
    }
}
