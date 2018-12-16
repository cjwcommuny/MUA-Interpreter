package mua.exception;

public class MuaIllegalFunctionParameterException extends MuaException {
    public MuaIllegalFunctionParameterException(String functionName) {
        super(functionName + " has illegal parameter", Level.ERROR);
    }
}
