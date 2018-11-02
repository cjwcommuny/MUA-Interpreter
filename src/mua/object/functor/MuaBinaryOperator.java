package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.MuaObject;

public class MuaBinaryOperator extends MuaFunctor {
    private static final int ARGUMENT_NUM = 2;
    public MuaBinaryOperator(String funcName, boolean isBuiltIn) {
        super(funcName, true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException, MuaDivideOrModuleZeroException {

    }
}
