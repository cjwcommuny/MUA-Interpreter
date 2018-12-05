package mua.exception;

public class MuaObjectNotExistException extends MuaException {
    public MuaObjectNotExistException(String object) {
        super("can't find object '" + object + "'", Level.ERROR);
    }
}
