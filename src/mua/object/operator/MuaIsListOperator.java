package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaList;

public class MuaIsListOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        boolean isList = argumentList.get(0).getClass() == MuaList.class;
        return new MuaBool(isList);
    }

    public MuaIsListOperator() {
        super("islist", 1, true);
    }
}