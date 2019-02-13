package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaLessThanOperator extends MuaOperator implements CompareOperator {
    public static final String FUNC_NAME = "lt";
    private static final int ARGUMENT_NUM = 2;
    public MuaLessThanOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        MuaObject op1 = argumentList.get(0);
        MuaObject op2 = argumentList.get(1);

        return new MuaBool(compareTo(op1, op2) < 0);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
