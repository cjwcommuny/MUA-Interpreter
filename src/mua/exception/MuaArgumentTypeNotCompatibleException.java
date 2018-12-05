package mua.exception;

public class MuaArgumentTypeNotCompatibleException extends MuaException {
    public MuaArgumentTypeNotCompatibleException(String operator) {
        super("argument type " + operator + " not compatible.", Level.ERROR);
    }
}
