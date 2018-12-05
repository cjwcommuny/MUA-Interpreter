package mua.exception;

public class MuaBraceNotCompatibleException extends MuaIllegalExpressionException {
    public MuaBraceNotCompatibleException() {
        super("brace not compatible.", Level.ERROR);
    }
}
