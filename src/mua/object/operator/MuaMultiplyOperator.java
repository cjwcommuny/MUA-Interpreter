package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaNumber;

public class MuaMultiplyOperator extends MuaOperator {
    public static final String FUNC_NAME = "mul";
    private static final int ARGUMENT_NUM = 2;
    public MuaMultiplyOperator() {
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
        return new MuaNumber(op1 * op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
