package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;

public class MuaIsNumberOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        boolean isNumber = argumentList.get(0).getClass() == MuaNumber.class;
        return new MuaBool(isNumber);
    }

    public MuaIsNumberOperator() {
        super("isnumber", 1, true);
    }
}
