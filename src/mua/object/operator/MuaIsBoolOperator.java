package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;

public class MuaIsBoolOperator extends MuaOperator {
    public MuaIsBoolOperator() {
        super("isbool", 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        boolean isBool = argumentList.get(0).getClass() == MuaBool.class;
        return new MuaBool(isBool);
    }
}
