package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;

public class MuaEqualOperator extends MuaFunctor {
    public static final String FUNC_NAME = "eq";
    private static final int ARGUMENT_NUM = 2;
    public MuaEqualOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException {
        checkArgumentNum(argumentList);
        MuaObject op1 = argumentList.get(0);
        MuaObject op2 = argumentList.get(1);
        boolean isEqual = op1.equals(op2);
        return new MuaBool(isEqual);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
