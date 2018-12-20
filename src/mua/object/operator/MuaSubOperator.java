package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaNumber;

public class MuaSubOperator extends MuaOperator implements ArithmeticOperator {
    public static final String FUNC_NAME = "sub";
    private static final int ARGUMENT_NUM = 2;
    public MuaSubOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        double op1, op2;
        op1 = convertToDouble(argumentList.get(0));
        op2 = convertToDouble(argumentList.get(1));
        return new MuaNumber(op1 - op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
