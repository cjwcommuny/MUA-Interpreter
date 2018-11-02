package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.MuaBool;
import mua.object.MuaObject;

public class MuaEqualOperator extends MuaFunctor {
    private static final String FUNC_NAME = "eq";
    private static final int ARGUMENT_NUM = 2;
    public MuaEqualOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
        if (argumentList.size() != getArgumentNum()) {
            throw new MuaArgumentNumNotCompatibleException();
        }
        MuaObject op1, op2;
        try {
            op1 = argumentList.get(0);
            op2 = argumentList.get(1);
            return new MuaBool(op1.equals(op2));
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
