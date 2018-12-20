package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaAddOperator extends MuaOperator implements ArithmeticOperator {
    public static final String FUNC_NAME = "add";
    private static final int ARGUMENT_NUM = 2;
    public MuaAddOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        MuaObject arg1 = argumentList.get(0);
        MuaObject arg2 = argumentList.get(1);

        double op1 = convertToDouble(arg1);
        double op2 = convertToDouble(arg2);
        return new MuaNumber(op1 + op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
