package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.primitive.MuaBool;
import mua.object.MuaObject;

public class MuaNotOperator extends MuaOperator {
    public static final String FUNC_NAME = "not";
    private static final int ARGUMENT_NUM = 1;
    public MuaNotOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList)
            throws MuaArgumentTypeNotCompatibleException {
        boolean op;
        try {
            op = ((MuaBool) argumentList.get(0)).getValue();
            return new MuaBool(!op);
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
