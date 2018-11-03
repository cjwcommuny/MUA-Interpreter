package mua.exception;

public class MuaObjectNotExistException extends MuaException {
    public MuaObjectNotExistException() {
        super("ERROR: can't find object.");
    }
}
