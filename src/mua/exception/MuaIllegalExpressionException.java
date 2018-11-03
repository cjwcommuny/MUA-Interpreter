package mua.exception;

public class MuaIllegalExpressionException extends MuaException {
    public MuaIllegalExpressionException() {
        super("ERROR: illegal expression.");
    }

    public MuaIllegalExpressionException(String message) {
        super(message);
    }
}
