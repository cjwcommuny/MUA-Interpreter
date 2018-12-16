package mua.object.operator;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaLessThanOperator extends MuaOperator {
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

        //TODO: could be refined
        if (op1.getClass() == MuaNumber.class && op2.getClass() == MuaNumber.class) {
            return new MuaBool(((MuaNumber) op1).compareTo((MuaNumber) op2) < 0);
        } else if (op1.getClass() == MuaWord.class && op2.getClass() == MuaWord.class) {
            return new MuaBool(((MuaWord) op1).compareTo((MuaWord) op2) < 0);
        } else {
            throw new MuaArgumentTypeNotCompatibleException(this.toString());
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
