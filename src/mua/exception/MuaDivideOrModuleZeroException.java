package mua.exception;

public class MuaDivideOrModuleZeroException extends MuaException {
    public MuaDivideOrModuleZeroException() {
        super("ERROR: divide or modulo by zero.");
    }
}
