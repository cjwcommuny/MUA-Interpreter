package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;

public class MuaOrOperator extends MuaFunctor {
    public static final String FUNC_NAME = "or";
    private static final int ARGUMENT_NUM = 2;
    public MuaOrOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
        if (argumentList.size() != getArgumentNum()) {
            throw new MuaArgumentNumNotCompatibleException();
        }
        boolean op1, op2;
        try {
            op1 = ((MuaBool) argumentList.get(0)).getValue();
            op2 = ((MuaBool) argumentList.get(1)).getValue();
            return new MuaBool(op1 || op2);
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
