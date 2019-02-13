package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;

public class MuaOrOperator extends MuaOperator implements BoolOperator {
    public static final String FUNC_NAME = "or";
    private static final int ARGUMENT_NUM = 2;
    public MuaOrOperator() {
    super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        boolean op1, op2;
        op1 = convertToBool(argumentList.get(0));
        op2 = convertToBool(argumentList.get(1));
        return new MuaBool(op1 || op2);
    }
    //TODO: merge all similar operation of arithmetic operation
    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
