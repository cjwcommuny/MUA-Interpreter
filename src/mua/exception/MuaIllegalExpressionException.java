package mua.exception;

public class MuaIllegalExpressionException extends MuaException {
    public MuaIllegalExpressionException() {
        super("ERROR: illegal expression.", Level.ERROR);
    }

    public MuaIllegalExpressionException(String message, Level level) {
        super(message, level);
    }
}
