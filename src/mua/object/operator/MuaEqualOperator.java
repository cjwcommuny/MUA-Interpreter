package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;

public class MuaEqualOperator extends MuaOperator implements CompareOperator {
    public static final String FUNC_NAME = "eq";
    private static final int ARGUMENT_NUM = 2;
    public MuaEqualOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        MuaObject arg1 = argumentList.get(0);
        MuaObject arg2 = argumentList.get(1);

        return new MuaBool(compareTo(arg1, arg2) == 0);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
