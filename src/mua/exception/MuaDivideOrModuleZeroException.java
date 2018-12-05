package mua.exception;

public class MuaDivideOrModuleZeroException extends MuaException {
    public MuaDivideOrModuleZeroException(String expression) {
        super("divide or modulo by zero: " + expression, Level.ERROR);
    }
}
