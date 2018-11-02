package mua.exception;

public class MuaDivideOrModuleZeroException extends Exception {
    public MuaDivideOrModuleZeroException() {
        super("ERROR: divide or modulo by zero.");
    }
}
