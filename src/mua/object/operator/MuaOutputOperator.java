package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;

public class MuaOutputOperator extends MuaOperator {
    public MuaOutputOperator() {
        super("output", 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
//        instanceReturnValue = argumentList.get(0);
        return argumentList.get(0);
    }
}
