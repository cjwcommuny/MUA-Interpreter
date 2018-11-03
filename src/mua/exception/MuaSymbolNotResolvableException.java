package mua.exception;

public class MuaSymbolNotResolvableException extends MuaException {
    public MuaSymbolNotResolvableException() {
        super("ERROR: can't resolve symbol.");
    }
}
