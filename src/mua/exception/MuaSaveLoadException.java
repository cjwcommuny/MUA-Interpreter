package mua.exception;

public class MuaSaveLoadException extends MuaException {
    public MuaSaveLoadException() {
        super("Save or Load namespace error.", Level.ERROR);
    }
}
