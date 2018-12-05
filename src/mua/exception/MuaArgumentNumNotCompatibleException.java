package mua.exception;

public class MuaArgumentNumNotCompatibleException extends MuaException {
    public MuaArgumentNumNotCompatibleException(String operator) {
        super("Argument number of " + operator + " not compatible.", Level.ERROR);
    }
}
