package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;

public class MuaAndOperator extends MuaOperator implements BoolOperator {
    public static final String FUNC_NAME = "and";
    private static final int ARGUMENT_NUM = 2;
    public MuaAndOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        MuaObject arg1 = argumentList.get(0);
        MuaObject arg2 = argumentList.get(1);

        boolean op1 = convertToBool(arg1);
        boolean op2 = convertToBool(arg2);
        return new MuaBool(op1 && op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
