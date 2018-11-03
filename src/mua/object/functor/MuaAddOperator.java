package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;

public class MuaAddOperator extends MuaFunctor {
    public static final String FUNC_NAME = "add";
    private static final int ARGUMENT_NUM = 2;
    public MuaAddOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
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
        return new MuaNumber(op1 + op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
