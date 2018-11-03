package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.*;

public class MuaModuloOperator extends MuaFunctor {
    public static final String FUNC_NAME = "mod";
    private static final int ARGUMENT_NUM = 2;
    private static final double epsilon = 0.0001; //todo: the location should be changed
    public MuaModuloOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException, MuaDivideOrModuleZeroException {
        if (argumentList.size() != getArgumentNum()) {
            throw new MuaArgumentNumNotCompatibleException();
        }
        double op1, op2;
        try {
            op1 = ((MuaNumber) argumentList.get(0)).getValue();
            op2 = ((MuaNumber) argumentList.get(1)).getValue();
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        if (op2 < epsilon && op2 > -epsilon) {
            throw new MuaDivideOrModuleZeroException();
        }
        return new MuaNumber(op1 % op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}

