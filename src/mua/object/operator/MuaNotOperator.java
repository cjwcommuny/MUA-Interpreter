package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.primitive.MuaBool;
import mua.object.MuaObject;

public class MuaNotOperator extends MuaOperator implements BoolOperator {
    public static final String FUNC_NAME = "not";
    private static final int ARGUMENT_NUM = 1;
    public MuaNotOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        boolean op;
        op = convertToBool(argumentList.get(0));
        return new MuaBool(!op);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
