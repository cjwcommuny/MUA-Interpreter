package mua.exception;

public class MuaSymbolNotResolvableException extends MuaException {
    public MuaSymbolNotResolvableException(String symbol) {
        super("can't resolve symbol '" + symbol + "'", Level.ERROR);
    }
}
