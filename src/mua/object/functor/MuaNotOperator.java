package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.MuaBool;
import mua.object.MuaObject;

public class MuaNotOperator extends MuaFunctor {
    public static final String FUNC_NAME = "not";
    private static final int ARGUMENT_NUM = 1;
    public MuaNotOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
        if (argumentList.size() != getArgumentNum()) {
            throw new MuaArgumentNumNotCompatibleException();
        }
        boolean op;
        try {
            op = ((MuaBool) argumentList.get(0)).getValue();
            return new MuaBool(!op);
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
