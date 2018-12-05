package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;

public class MuaOrOperator extends MuaOperator {
    public static final String FUNC_NAME = "or";
    private static final int ARGUMENT_NUM = 2;
    public MuaOrOperator() {
    super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        boolean op1, op2;
        try {
            op1 = ((MuaBool) argumentList.get(0)).getValue();
            op2 = ((MuaBool) argumentList.get(1)).getValue();
            return new MuaBool(op1 || op2);
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }
    //TODO: merge all similar operation of arithmetic operation
    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
