package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;

public class MuaSubOperator extends MuaFunctor {
    public static final String FUNC_NAME = "sub";
    private static final int ARGUMENT_NUM = 2;
    public MuaSubOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
        checkArgumentNum(argumentList);
        double op1, op2;
        try {
            op1 = ((MuaNumber) argumentList.get(0)).getValue();
            op2 = ((MuaNumber) argumentList.get(1)).getValue();
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        return new MuaNumber(op1 - op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
